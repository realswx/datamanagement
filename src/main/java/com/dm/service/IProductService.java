package com.dm.service;

import com.dm.pojo.Product;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IProductService {

    /**
     * 保存商品信息到数据库
     * @param productList
     */
    void saveBatch(List<Product> productList);

    /**
     * 查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize);

}
