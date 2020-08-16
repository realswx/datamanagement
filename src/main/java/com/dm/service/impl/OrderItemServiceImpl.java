package com.dm.service.impl;

import com.dm.mapper.OrderItemMapper;
import com.dm.pojo.OrderItem;
import com.dm.service.IOrderItemService;
import com.dm.vo.OrderItemVo;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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


    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderItem> orderItemList = orderItemMapper.selectAll();
        List<OrderItemVo> orderItemVoList = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtils.copyProperties(orderItem, orderItemVo);
            orderItemVoList.add(orderItemVo);
        }
        log.info("orderItemList={}", orderItemList);

        PageInfo pageInfo = new PageInfo<>(orderItemList);
        pageInfo.setList(orderItemVoList);
        return ResponseVo.success(pageInfo);
    }
}
