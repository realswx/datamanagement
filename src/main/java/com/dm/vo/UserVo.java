package com.dm.vo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {

    private Integer id;

    private String username;

    private String password;

    private String email;

    private String roleStr;

    private Date birthday;

}
