package com.dm.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

    // 主键id
    @ExcelIgnore // 生成报表时忽略，不生成此字段
    private Integer id;

    @ExcelProperty(value = "订单编号", index = 0) // 定义表头名称和位置,0代表第一列
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "订单编号")
    private Integer orderNo;

    @ExcelProperty(value = "买家ID", index = 1)
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "买家ID")
    private Integer buyerId;

    @ExcelProperty(value = "商品ID", index = 2)
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "商品ID")
    private Integer productId;

    @ExcelProperty(value = "商品名称", index = 3)
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "商品名称")
    private String productName;

    @ExcelProperty(value = "商品单价", index = 4)
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "商品单价")
    private BigDecimal unitPrice;

    @ExcelProperty(value = "商品数量", index = 5)
    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "商品数量")
    private Integer quantity;

    @ExcelProperty(value = "金额", index = 6)
    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "金额")
    private BigDecimal totalPrice;



}