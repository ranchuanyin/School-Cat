package com.ranchuanyin.schoolcat.filter;


import com.ranchuanyin.schoolcat.mapper.CatAccountMapper;
import com.ranchuanyin.schoolcat.util.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 请求拦截器
 * 被拦截的请求进行是否登录证明
 */
@Component
public class AuthorizeFilter extends OncePerRequestFilter {
    @Resource
    CatAccountMapper mapper;
    @Resource
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
