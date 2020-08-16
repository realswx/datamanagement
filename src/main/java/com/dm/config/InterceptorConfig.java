package com.dm.config;


import com.dm.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器Interceptor的配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())

                .addPathPatterns("/**") //默认拦截所有url
                .excludePathPatterns("/error", "/user/login",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**",

                        //TODO 做完功能后删除
                        "/order/**", "/product/**", "/orderItem/**"); //这些不拦截


    }
}
