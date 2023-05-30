package com.ranchuanyin.schoolcat.common.config;

import com.alibaba.fastjson.JSONObject;
import com.ranchuanyin.schoolcat.common.toolclass.RestBean;
import com.ranchuanyin.schoolcat.generator.user.service.impl.AuthenticationServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Security配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    AuthenticationServiceImpl authenticationService;
    @Resource
    private DataSource dataSource;

    //配置密码的加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



    //设置记住密码的时候，token保存在一个表里面
    //这里使用JDBC来进行创建
    @Bean
    protected PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
    //设置AuthenticationManager，从中获取Authentication对象，并且重写userDetailsService的方法
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
        return security.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .and()
                .build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
    PersistentTokenRepository tokenRepository) throws Exception {
        return http.csrf().disable()//禁止csrf
                .cors().configurationSource(this.corsConfigurationSource())//配置cors跨域
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/cat/auth/**","/cat/count/**").permitAll()//配置URL不需要登录就可以访问
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginProcessingUrl("/cat/auth/login")//配置登录URL
                .successHandler(this::onAuthenticationSuccess)//配置登录成功信息提醒
                .failureHandler(this::onAuthenticationFailure)//配置失败信息
                .and()
                .exceptionHandling()//配置同一异常
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .logout().logoutUrl("/cat/auth/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(3600 * 24 * 7)
                .and()
                .build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOriginPattern("*");
        cors.setAllowCredentials(true);
        cors.addAllowedMethod("*");
        cors.addExposedHeader("*");
        cors.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",cors);
        return source;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("utf-8");
        if (request.getRequestURI().endsWith("/login")){

            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登陆成功")));
        }else if (request.getRequestURI().endsWith("/logout")){

            response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出成功")));
        }

    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401,exception.getMessage())));
    }
}
