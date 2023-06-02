package com.ranchuanyin.schoolcat.common.interceptor;


import com.ranchuanyin.schoolcat.common.toolclass.LoginAndRun;
import com.ranchuanyin.schoolcat.common.toolclass.RedisCache;
import com.ranchuanyin.schoolcat.generator.user.domain.User;
import com.ranchuanyin.schoolcat.generator.user.mapper.CatAccountMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 请求拦截器
 * 被拦截的请求进行是否登录证明
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    @Resource
    CatAccountMapper mapper;
    @Resource
    RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取登录者信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User account = (User)authentication.getPrincipal();
        //登录量计数
        String loginsAndTasks = account.getCatAccount().getId().toString()+"Task";
        LoginAndRun loginAndRun = redisCache.getCacheObject(loginsAndTasks);
        if (Objects.isNull(loginAndRun)){
            loginAndRun = new LoginAndRun();
            loginAndRun.setId(account.getCatAccount().getId());
            loginAndRun.setLogin(true);
            loginAndRun.setRelease(false);
            int i = mapper.IncreaseExperience(account.getCatAccount().getId(), 10);
            account.getCatAccount().setExperience(account.getCatAccount().getExperience()+10);
            redisCache.setCacheObject(loginsAndTasks,loginAndRun,24, TimeUnit.HOURS);
            redisCache.incrementValue("LoginNum",1);
        }
        return true;
    }
}
