package com.dm.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVo {

    private Integer id;

    private Integer orderNo;

    private Integer buyerId;

    private BigDecimal payment;

    private Date createTime;

}
