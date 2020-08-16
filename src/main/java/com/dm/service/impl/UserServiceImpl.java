package com.dm.service.impl;

import com.dm.enums.ResponseEnum;
import com.dm.mapper.UserMapper;
import com.dm.pojo.User;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
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
}
