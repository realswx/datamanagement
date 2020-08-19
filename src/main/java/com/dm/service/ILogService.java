package com.dm.service;

import com.dm.pojo.Log;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ILogService {


    /**
     * 保存日志数据
     * @param username 用户
     * @param browser 浏览器
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志实体
     */
    @Async
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log);


    /**
     * 查询全部数据
     * @param
     * @return /
     */
    ResponseVo<PageInfo> queryAll(Integer pageNum, Integer pageSize, String logType);



}
