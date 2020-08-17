package com.dm.mapper;

import com.dm.pojo.User;

import java.util.List;

public interface UserMapper {


    /**
     * 保存用户信息到数据库
     * @param userList
     */
    void saveBatch(List<User> userList);

    //登录时，判断用户是否存在
    User selectByUsername(String username);

    /**
     * 查询商品列表
     */
    List<User> selectAll(Integer id);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    int countByUsername(String username);

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    int countByEmail(String email);

    /**
     * 添加用户
     * @param record
     * @return
     */
    int insertSelective(User record);



    int deleteByPrimaryKey(Integer id);

    int insert(User record);



    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}