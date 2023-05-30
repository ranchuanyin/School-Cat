package com.ranchuanyin.schoolcat.generator.user.controller;


import com.ranchuanyin.schoolcat.common.toolclass.RedisCache;
import com.ranchuanyin.schoolcat.common.toolclass.RestBean;
import com.ranchuanyin.schoolcat.generator.user.domain.CatAccount;
import com.ranchuanyin.schoolcat.generator.user.domain.User;
import jakarta.annotation.Resource;
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
    public RestBean<CatAccount> me(@SessionAttribute("account") User user){
        return RestBean.success(user.getCatAccount());
    }


}
