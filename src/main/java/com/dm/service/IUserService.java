package com.dm.service;

import com.dm.form.LoginUserUpdateForm;
import com.dm.form.UserUpdateForm;
import com.dm.pojo.User;
import com.dm.vo.ResponseVo;
import com.dm.vo.UserVo;
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
    ResponseVo<UserVo> login(String username, String password);

    /**
     * 查询除登录用户外的所有系统用户
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer id, Integer pageNum, Integer pageSize);

    /**
     * 添加用户
     * @param user
     * @return
     */
    ResponseVo<UserVo> add(User user);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ResponseVo update(User user);



    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    ResponseVo<User> findById(Integer id);


    /**
     * 修改用户权限
     * @param currentUser
     * @param updateId
     * @param roleStr
     * @return
     */
    ResponseVo updateRole(User currentUser, Integer updateId, String roleStr);





}
