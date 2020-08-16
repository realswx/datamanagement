package com.dm.service;

import com.dm.pojo.OrderItem;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderItemService {

    /**
     * 保存订单明细信息到数据库
     * @param orderItemList
     */
    void saveBatch(List<OrderItem> orderItemList);

    /**
     * 查询订单明细表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize);

}
