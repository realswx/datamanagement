package com.dm.service.impl;

import com.dm.consts.DMConst;
import com.dm.enums.ResponseEnum;
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
    public ResponseVo<User> login(String username, String password) {
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
        return ResponseVo.success(user);
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
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll(id);
        List<UserVo> userVoList = new ArrayList<>();
        for (User user : userList) {
            UserVo userVo = new UserVo();
            if (user.getRole() == 2){
                userVo.setRoleStr(DMConst.TOP_ADMIN);
            } else if (user.getRole() == 1){
                userVo.setRoleStr(DMConst.ADMIN);
            } else {
                userVo.setRoleStr(DMConst.ORD_USER);
            }
            BeanUtils.copyProperties(user, userVo);
            //防止密码泄露
            userVo.setPassword("");
            userVoList.add(userVo);
        }
//        log.info("userList={}", userList);
        log.info("userVoList={}", userVoList);

        PageInfo pageInfo = new PageInfo<>(userList);
        pageInfo.setList(userVoList);
        return ResponseVo.success(pageInfo);

    }
}
