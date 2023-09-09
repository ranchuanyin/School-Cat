package com.ranchuanyin.schoolcat.config;


import com.ranchuanyin.schoolcat.filter.AuthorizeFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器，拦截加入的请求
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Resource
    AuthorizeFilter interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
