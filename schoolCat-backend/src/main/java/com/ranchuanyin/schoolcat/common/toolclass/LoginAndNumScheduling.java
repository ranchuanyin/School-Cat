package com.ranchuanyin.schoolcat.common.toolclass;


import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoginAndNumScheduling {
    @Resource
    RedisCache redisCache;
    @Scheduled(cron = "0 0 8 * * ?")
    public void InitializeLoginNum(){
        redisCache.setCacheObject("LoginNum",0);
    }
}
