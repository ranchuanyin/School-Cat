package com.ranchuanyin.schoolcat.config;

import com.alibaba.fastjson.JSONObject;
import com.ranchuanyin.schoolcat.units.RestBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, "用户未登录或者登录已经过期")));
    }
}
