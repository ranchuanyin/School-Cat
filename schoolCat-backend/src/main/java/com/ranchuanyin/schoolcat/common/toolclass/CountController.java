package com.ranchuanyin.schoolcat.common.toolclass;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat/count")
public class CountController {
    @Resource
    RedisCache redisCache;

    @GetMapping("/num")
    public RestBean<Integer> num(){
        Integer loginNum = redisCache.getCacheObject("LoginNum");

        return RestBean.success(loginNum);
    }
}
