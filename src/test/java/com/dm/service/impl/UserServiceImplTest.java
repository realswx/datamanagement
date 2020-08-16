package com.dm.service.impl;

import com.dm.DatamanagementApplicationTests;
import com.dm.enums.ResponseEnum;
import com.dm.service.IUserService;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class UserServiceImplTest extends DatamanagementApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void list() {
        ResponseVo<PageInfo> responseVo = userService.list(1, 1, 10);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}