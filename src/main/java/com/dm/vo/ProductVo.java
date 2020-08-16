package com.dm.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVo {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Integer sale;

}
