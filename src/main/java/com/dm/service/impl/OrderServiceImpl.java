package com.dm.service.impl;

import com.dm.mapper.OrderMapper;
import com.dm.pojo.Order;
import com.dm.service.IOrderService;
import com.dm.vo.OrderVo;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
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

    /**
     * 查询订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAll();
        List<OrderVo> orderVoList = new ArrayList<>();
        orderVoList = orderList.stream()
                .map(e -> {
                    OrderVo orderVo = new OrderVo();
                    BeanUtils.copyProperties(e, orderVo);
                    return orderVo;
                }).collect(Collectors.toList());
        log.info("orderList={}", orderList);

        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderVoList);
        return ResponseVo.success(pageInfo);
    }
}
