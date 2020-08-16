package com.dm.controller;


import com.alibaba.excel.EasyExcel;
import com.dm.consts.DMConst;
import com.dm.enums.ResponseEnum;
import com.dm.listener.ProductDataListener;
import com.dm.pojo.Product;
import com.dm.service.IProductService;
import com.dm.util.OpenCsvUtil;
import com.dm.vo.ResponseVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
@Api(tags = "Product模块类")
public class ProductController {

    @Autowired
    private IProductService productService;


    // 导入文件
    @PostMapping("/import")
    public ResponseVo<Product> importFile(@RequestBody MultipartFile file) {
        if (StringUtils.isEmpty(file.getName())){
            return ResponseVo.error(ResponseEnum.IMPORT_ERROR, "系统未获取到文件！");
        }
        log.info("正在导入文件：{}", file.getOriginalFilename());

//        return ImportProductFileUtil.importFile(file, productService);

        //截取文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //通过后缀名判断文件类型，进行对应导入操作
        if (DMConst.CSV.equals(suffix.toUpperCase())){ //导入csv文件
            List<Product> data = OpenCsvUtil.getCsvData(file, Product.class);
            productService.saveBatch(data);
        } else { //导入excel文件
            try {
                EasyExcel.read(file.getInputStream(), Product.class, new ProductDataListener(productService)).sheet().doRead();
            } catch (IOException e) {
                return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
            }
        }
        return ResponseVo.success();
    }

}
