package com.ranchuanyin.schoolcat.generator.user.controller;


import com.ranchuanyin.schoolcat.common.toolclass.RedisCache;
import com.ranchuanyin.schoolcat.common.toolclass.RestBean;
import com.ranchuanyin.schoolcat.generator.user.domain.CatAccount;
import com.ranchuanyin.schoolcat.generator.user.domain.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/cat/user")
public class UserController {

    @Resource
    RedisCache redisCache;

    @GetMapping("/me")
    public RestBean<CatAccount> me(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User account = (User)authentication.getPrincipal();
        String loginUser = "login:"+account.getCatAccount().getId();
        CatAccount cacheObject = redisCache.getCacheObject(loginUser);
        return RestBean.success(cacheObject);
    }


}
