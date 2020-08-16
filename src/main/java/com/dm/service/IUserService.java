package com.dm.service;

import com.dm.pojo.User;
import com.dm.vo.ResponseVo;

import java.util.List;

public interface IUserService {

    /**
     * 保存用户信息到数据库
     * @param userList
     */
    void saveBatch(List<User> userList);

    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);

}
