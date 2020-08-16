package com.dm.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemVo {

    private Integer id;

    private Integer orderNo;

    private Integer buyerId;

    private Integer productId;

    private String productName;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

}
