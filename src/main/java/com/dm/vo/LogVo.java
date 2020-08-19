package com.dm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogVo {

    //日志id
    private Integer logId;

    //描述
    private String description;

    //日志类型
    private String logType;

    //方法名
    private String method;

    //参数
    private String params;

    //请求ip
    private String requestIp;

    //请求耗时
    private Long time;

    //操作用户
    private String username;

    //地址
    private String address;

    //浏览器
    private String browser;

    //创建日期
    private Date createTime;

    //异常详细
    private String exceptionDetail;

}
