package com.dm.mapper;

import com.dm.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper {


    /**
     * 保存订单明细信息到数据库
     * @param orderItemList
     */
    void saveBatch(List<OrderItem> orderItemList);


    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}