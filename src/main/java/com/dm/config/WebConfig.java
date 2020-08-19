package com.dm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 全局配置类，配置跨域请求
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * 1.允许访问路径
         * 2.请求来源
         * 3.方法
         * 4.允许携带，如token
         * 5.最大响应时间
         */
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8005", "null")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true) //允许携带一些信息
                .maxAge(3600); //最大响应时间
    }
}
