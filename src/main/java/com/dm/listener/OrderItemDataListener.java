package com.dm.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dm.pojo.OrderItem;
import com.dm.service.IOrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderItemDataListener extends AnalysisEventListener<OrderItem> {

    @Autowired
    private IOrderItemService orderItemService;

    public OrderItemDataListener(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<OrderItem> orderItemList = new ArrayList<>();


    @Override
    public void invoke(OrderItem data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        orderItemList.add(data);
        if (orderItemList.size() >= BATCH_COUNT) {
            saveData();
            orderItemList.clear();
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
        log.info("{}条数据，开始存储数据库！", orderItemList.size());
        if (!CollectionUtils.isEmpty(orderItemList)) {
            orderItemService.saveBatch(orderItemList);
        }
        log.info("存储数据库成功！");
    }

}
