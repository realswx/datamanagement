package com.dm.controller;

import com.alibaba.excel.EasyExcel;
import com.dm.consts.DMConst;
import com.dm.enums.ResponseEnum;
import com.dm.listener.OrderItemDataListener;
import com.dm.pojo.OrderItem;
import com.dm.service.IOrderItemService;
import com.dm.util.OpenCsvUtil;
import com.dm.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/orderItem")
@Api(tags = "OrderItem模块类")
public class OrderItemController {

    @Autowired
    private IOrderItemService orderItemService;

    // 导入文件
    @PostMapping("/import")
    public ResponseVo<OrderItem> importFile(@RequestBody MultipartFile file) {
        if (StringUtils.isEmpty(file.getName())){
            return ResponseVo.error(ResponseEnum.IMPORT_ERROR, "系统未获取到文件！");
        }
        log.info("正在导入文件：{}", file.getOriginalFilename());

//        return ImportOrderFileUtil.importFile(file, orderService);

        //截取文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //通过后缀名判断文件类型，进行对应导入操作
        if (DMConst.CSV.equals(suffix.toUpperCase())){ //导入csv文件
            List<OrderItem> data = OpenCsvUtil.getCsvData(file, OrderItem.class);
            orderItemService.saveBatch(data);
        } else { //导入excel文件
            try {
                EasyExcel.read(file.getInputStream(), OrderItem.class, new OrderItemDataListener(orderItemService)).sheet().doRead();
            } catch (IOException e) {
                return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
            }
        }
        return ResponseVo.success();
    }


    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return orderItemService.list(pageNum, pageSize);
    }


}
