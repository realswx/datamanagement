package com.dm.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class Product {

    @ExcelProperty(value = "商品ID", index = 0) // 定义表头名称和位置,0代表第一列
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "商品ID")
    private Integer id;

    @ExcelProperty(value = "商品名称", index = 1)
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "商品名称")
    private String name;

    @ExcelProperty(value = "价格", index = 2)
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "价格")
    private BigDecimal price;

    @ExcelProperty(value = "库存", index = 3)
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "库存")
    private Integer stock;

    @ExcelProperty(value = "销售量", index = 4)
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "销售量")
    private Integer sale;


}