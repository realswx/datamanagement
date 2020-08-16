package com.dm.service.impl;

import com.dm.mapper.OrderItemMapper;
import com.dm.pojo.OrderItem;
import com.dm.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;


    /**
     * 保存订单明细信息到数据库
     * @param orderItemList
     */
    @Override
    public void saveBatch(List<OrderItem> orderItemList) {
        orderItemMapper.saveBatch(orderItemList);
    }
}
