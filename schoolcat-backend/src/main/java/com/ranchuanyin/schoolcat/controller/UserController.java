package com.ranchuanyin.schoolcat.controller;


import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.util.JwtUtil;
import com.ranchuanyin.schoolcat.util.RedisCache;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/cat/common")
public class UserController {

    @Resource
    RedisCache redisCache;
    @Resource
    private JwtUtil jwtUtil;

    @GetMapping("/JWT")
    public RestBean<Integer> count(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (Objects.nonNull(authorization)) {
            if (jwtUtil.validate(authorization)) {
                return RestBean.success();
            } else {
                return RestBean.failure(401, "登录已失效");
            }
        }
        return RestBean.failure(401, "未登录");
    }
}
