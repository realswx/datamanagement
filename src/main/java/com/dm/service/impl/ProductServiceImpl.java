package com.dm.service.impl;

import com.dm.mapper.ProductMapper;
import com.dm.pojo.Product;
import com.dm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;


    /**
     * 保存商品信息到数据库
     * @param productList
     */
    @Override
    public void saveBatch(List<Product> productList) {
        productMapper.saveBatch(productList);
    }
}
