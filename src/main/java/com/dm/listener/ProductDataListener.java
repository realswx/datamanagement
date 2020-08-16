package com.dm.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dm.pojo.Product;
import com.dm.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductDataListener extends AnalysisEventListener<Product> {

    @Autowired
    private IProductService productService;

    public ProductDataListener(IProductService productService) {
        this.productService = productService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Product> productList = new ArrayList<>();


    @Override
    public void invoke(Product data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        productList.add(data);
        if (productList.size() >= BATCH_COUNT) {
            saveData();
            productList.clear();
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
        log.info("{}条数据，开始存储数据库！", productList.size());
        if (!CollectionUtils.isEmpty(productList)) {
            productService.saveBatch(productList);
        }
        log.info("存储数据库成功！");
    }

}
