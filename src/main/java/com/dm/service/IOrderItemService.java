package com.dm.service;

import com.dm.pojo.OrderItem;

import java.util.List;

public interface IOrderItemService {

    /**
     * 保存订单明细信息到数据库
     * @param orderItemList
     */
    void saveBatch(List<OrderItem> orderItemList);

}
