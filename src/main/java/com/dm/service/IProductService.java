package com.dm.service;

import com.dm.pojo.Order;
import com.dm.pojo.Product;

import java.util.List;

public interface IProductService {

    /**
     * 保存商品信息到数据库
     * @param productList
     */
    void saveBatch(List<Product> productList);

}
