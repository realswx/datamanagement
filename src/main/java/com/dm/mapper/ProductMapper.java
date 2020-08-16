package com.dm.mapper;

import com.dm.pojo.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 保存订单信息到数据库
     * @param productList
     */
    void saveBatch(List<Product> productList);



    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}