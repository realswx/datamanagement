package com.dm.mapper;

import com.dm.pojo.Order;
import com.dm.pojo.User;

import java.util.List;

public interface OrderMapper {

    /**
     * 保存订单信息到数据库
     * @param orderList
     */
    void saveBatch(List<Order> orderList);



    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}