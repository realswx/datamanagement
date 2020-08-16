package com.dm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dm.mapper")
public class DatamanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatamanagementApplication.class, args);
    }

}
