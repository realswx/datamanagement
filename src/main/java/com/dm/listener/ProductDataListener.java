package com.dm.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.dm.pojo.Product;
import com.dm.service.IProductService;
import com.dm.service.impl.ProductServiceImpl;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 ProductDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去

@Slf4j
public class ProductDataListener extends AnalysisEventListener<Product> {

    @Autowired
    private IProductService productService;

    public ProductDataListener() {
        productService = new ProductServiceImpl();
    }

    public ProductDataListener(IProductService productService) {
        this.productService = productService;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Product> productList = new ArrayList<>();


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(Product data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        productList.add(data);
        if (productList.size() >= BATCH_COUNT) {
            saveData();
            productList.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
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
