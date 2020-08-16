package com.dm.service.impl;

import com.dm.mapper.OrderMapper;
import com.dm.pojo.Order;
import com.dm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private OrderMapper orderMapper;

    /**
     * 保存订单信息到数据库
     * @param orderList
     */
    @Override
    public void saveBatch(List<Order> orderList) {
        orderMapper.saveBatch(orderList);
    }
}
