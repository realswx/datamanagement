package com.dm.service.impl;

import com.dm.enums.ResponseEnum;
import com.dm.enums.RoleEnum;
import com.dm.form.LoginUserUpdateForm;
import com.dm.form.UserUpdateForm;
import com.dm.mapper.UserMapper;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import com.dm.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户信息到数据库
     * @param userList
     */
    @Override
    public void saveBatch(List<User> userList) {
        userMapper.saveBatch(userList);
    }


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResponseVo<UserVo> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null){
            //用户不存在,为了安全，返回用户名或密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))){
            //密码错误,为了安全，返回用户名或密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //登录成功后将密码设置为空，防止密码加入json返回给前端
        user.setPassword("");
        //登录成功
        UserVo userVo = user2UserVo(user);
        return ResponseVo.success(userVo);
    }


    /**
     * 查询除登录用户外的所有系统用户
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseVo<PageInfo> list(Integer id, Integer pageNum, Integer pageSize) {
        //error();

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll(id);
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            UserVo userVo = user2UserVo(user);
            userVoList.add(userVo);
        }
//        log.info("userList={}", userList);
//        log.info("userVoList={}", userVoList);

        PageInfo pageInfo = new PageInfo<>(userList);
        pageInfo.setList(userVoList);
        return ResponseVo.success(pageInfo);
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public ResponseVo add(User user) {

        //username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0){
//            throw new RuntimeException("该名字username已添加");
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }
        //email不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0){
//            throw new RuntimeException("该邮箱email已添加");
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        //设置默认角色为普通用户
        user.setRole(RoleEnum.CUSTOMER.getCode());
        //密码，MD5摘要算法
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)));
        //写入数据库
        int resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
//            throw new RuntimeException("添加失败");
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();

    }


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public ResponseVo update(User user){
        //username不能重复
        int countByUsername = userMapper.countByUsernameAndId(user);
        if (countByUsername > 0){
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }
        int countByEmail = userMapper.countByEmailAndId(user);
        if (countByEmail > 0){
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

//        log.info("for.username={}", form.getUsername());
//        log.info("username={}", user.getUsername());
        int row = userMapper.updateByPrimaryKeySelective(user);
        if (row == 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }


    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    @Override
    public ResponseVo<User> findById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return ResponseVo.success(user);
    }


    /**
     * 修改用户权限
     * @param currentUser
     * @param updateId
     * @param roleStr
     * @return
     */
    //TODO
    @Override
    public ResponseVo updateRole(User currentUser, Integer updateId, String roleStr) {
        if (currentUser.getRole()==0){
            return ResponseVo.error(ResponseEnum.INSUFFICIENT_AUTHORITY);
        } else if (currentUser.getRole()==1 && roleStr.equals(RoleEnum.CUSTOMER.getDesc())) {
            return ResponseVo.error(ResponseEnum.INSUFFICIENT_AUTHORITY);
        }

        User user = new User();
        user.setId(updateId);
        if(roleStr.equals(RoleEnum.TOP_ADMIN.getDesc())){
            user.setRole(2);
        } else if (roleStr.equals(RoleEnum.ADMIN.getDesc())){
            user.setRole(1);
        } else {
            user.setRole(0);
        }
        int row = userMapper.updateByPrimaryKeySelective(user);
        if (row == 0){
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }






    private UserVo user2UserVo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        //防止密码泄露
        userVo.setPassword("");
        if (user.getRole() == 2){
            userVo.setRoleStr(RoleEnum.TOP_ADMIN.getDesc());
        } else if (user.getRole() == 1){
            userVo.setRoleStr(RoleEnum.ADMIN.getDesc());
        } else {
            userVo.setRoleStr(RoleEnum.CUSTOMER.getDesc());
        }

        return userVo;
    }

    //制造异常
    private void error(){
        throw new RuntimeException("意外错误");
    }
}
