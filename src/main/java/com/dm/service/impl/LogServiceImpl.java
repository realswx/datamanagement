package com.dm.service.impl;


import cn.hutool.json.JSONObject;
import com.dm.mapper.LogMapper;
import com.dm.pojo.Log;
import com.dm.service.ILogService;
import com.dm.vo.LogVo;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.dm.log.annotation.Log aopLog = method.getAnnotation(com.dm.log.annotation.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        List<Object> argValues = new ArrayList<>(Arrays.asList(joinPoint.getArgs()));
        //参数名称
        for (Object argValue : argValues) {
            params.append(argValue).append(" ");
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if (loginPath.equals(signature.getName())) {
            try {
                username = new JSONObject(argValues.get(0)).get("username").toString();
            } catch (Exception e) {
                LogServiceImpl.log.error(e.getMessage(), e);
            }
        }

        log.setAddress(ip); //注意，需要修改，通过ip查地址。StringUtil.getCityInfo(log.getRequestIp())
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        logMapper.save(log);
    }


    @Override
    public ResponseVo<PageInfo> queryAll(Integer pageNum, Integer pageSize, String logType) {
        PageHelper.startPage(pageNum, pageSize);
        List<Log> logList = logMapper.queryAllByLogType(logType);
        List<LogVo> logVoList = new ArrayList<>();
        logVoList = logList.stream()
                .map(e -> {
                    LogVo logVo = new LogVo();
                    BeanUtils.copyProperties(e, logVo);
                    return logVo;
                }).collect(Collectors.toList());
        log.info("logList={}", logList);

        PageInfo pageInfo = new PageInfo<>(logList);
        pageInfo.setList(logVoList);
        return ResponseVo.success(pageInfo);

    }
}
