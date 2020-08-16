package com.dm.service;

import com.dm.pojo.Order;
import com.dm.pojo.User;

import java.util.List;

public interface IOrderService {

    /**
     * 保存订单信息到数据库
     * @param orderList
     */
    void saveBatch(List<Order> orderList);

}
