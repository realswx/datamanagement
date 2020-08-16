package com.dm.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

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

    @ExcelProperty(value = "付款金额", index = 2)
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "付款金额")
    private BigDecimal payment;

    @ExcelProperty(value = "创建时间", index = 3)
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "创建时间")
    private Date createTime;


}