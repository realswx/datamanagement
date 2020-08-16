package com.dm.service;

import com.dm.pojo.User;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    /**
     * 保存用户信息到数据库
     * 不需要此接口
     * @param userList
     */
    void saveBatch(List<User> userList);

    /**
     * 登录
     */
    ResponseVo<User> login(String username, String password);

    /**
     * 查询除登录用户外的所有系统用户
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer id, Integer pageNum, Integer pageSize);

}
