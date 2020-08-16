package com.dm.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dm.pojo.Order;
import com.dm.pojo.User;
import com.dm.service.IOrderService;
import com.dm.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderDataListener extends AnalysisEventListener<Order> {

    @Autowired
    private IOrderService orderService;

    public OrderDataListener(IOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Order> orderList = new ArrayList<>();


    @Override
    public void invoke(Order data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        orderList.add(data);
        if (orderList.size() >= BATCH_COUNT) {
            saveData();
            orderList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", orderList.size());
        if (!CollectionUtils.isEmpty(orderList)) {
            orderService.saveBatch(orderList);
        }
        log.info("存储数据库成功！");
    }

}
