package com.dm.service;

import com.dm.pojo.Order;
import com.dm.pojo.User;
import com.dm.vo.OrderVo;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderService {

    /**
     * 保存订单信息到数据库
     * @param orderList
     */
    void saveBatch(List<Order> orderList);

    /**
     * 查询订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize);

}
