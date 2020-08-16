package com.dm.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;


@Data
public class User {

    // 主键id
    @ExcelIgnore // 生成报表时忽略，不生成此字段
    private Integer id;

    @ExcelProperty(value = "姓名", index = 0) // 定义表头名称和位置,0代表第一列
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "姓名")
    private String username;

    @ExcelProperty(value = "密码", index = 1)
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "密码")
    private String password;

    @ExcelProperty(value = "邮箱", index = 2)
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "邮箱")
    private String email;

    //1-管理员，0-普通用户，也可设置成Boolean类型
    @ExcelProperty(value = "角色", index = 3)
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "角色")
    private Integer role;

    @ColumnWidth(20) // 定义列宽
    @DateTimeFormat(value = "yyyy/MM/dd")
    @ExcelProperty(value = "出生日期", index = 4)
    @JsonFormat(pattern="yyyy/MM/dd",timezone = "GMT+8")
    @CsvDate("yyyy/MM/dd")
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "出生日期")
    private Date birthday;


}