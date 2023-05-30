package com.ranchuanyin.schoolcat.common.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个AOP注解，当添加这个注解的类，将可以执行经验增加的AOP切面
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExperienceAddsAnnotation {
}
