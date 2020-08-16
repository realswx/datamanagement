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


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}