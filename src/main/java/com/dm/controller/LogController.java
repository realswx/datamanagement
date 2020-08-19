package com.dm.controller;

import com.dm.log.annotation.Log;
import com.dm.service.ILogService;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Api(tags = "Log日志模块")
public class LogController {

    @Autowired
    private ILogService logService;



    @GetMapping("/info")
    @ApiOperation("日志查询")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功")
    })
    public ResponseVo<PageInfo> infoList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return logService.queryAll(pageNum, pageSize, "INFO");
    }



    @GetMapping(value = "/error")
    @ApiOperation("错误日志查询")
    @ApiResponses({
            @ApiResponse(code=0,message = "成功")
    })
    public ResponseVo<PageInfo> errorList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return logService.queryAll(pageNum, pageSize, "ERROR");
    }



}
