package com.dm.service.impl;

import com.dm.mapper.ProductMapper;
import com.dm.pojo.Product;
import com.dm.service.IProductService;
import com.dm.vo.ProductVo;
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


    /**
     * 查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectAll();
        List<ProductVo> productVoList = new ArrayList<>();
        productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                }).collect(Collectors.toList());
        log.info("productList={}", productList);

        PageInfo pageInfo = new PageInfo<>(productList);
        pageInfo.setList(productVoList);
        return ResponseVo.success(pageInfo);

    }



}
