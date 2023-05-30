package com.ranchuanyin.schoolcat.common.aop;

import com.ranchuanyin.schoolcat.generator.user.domain.CatAccount;

import com.ranchuanyin.schoolcat.generator.user.mapper.CatAccountMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class ExperienceAddsAspect {
    @Resource
    CatAccountMapper catAccountMapper;

    /**
     *一个环绕AOP，当执行修改经验的Service时，触发该AOP，修改当前Session中的值，达到一个类似响应式的结果
     */
    @Around("@annotation(com.ranchuanyin.schoolcat.common.aop.annotations.ExperienceAddsAnnotation) && args(..,session))")
    public Object ExperienceIncreases(ProceedingJoinPoint proceedingJoinPoint,HttpSession session) throws Throwable {
        Object proceed = proceedingJoinPoint.proceed();
        Object[] args = proceedingJoinPoint.getArgs();
        Long string = (Long) Objects.requireNonNull(Arrays.stream(args).findFirst().orElse(null));
        CatAccount catAccount = catAccountMapper.selectById(string);
        session.setAttribute("account",catAccount);
        return proceed;
    }
}
