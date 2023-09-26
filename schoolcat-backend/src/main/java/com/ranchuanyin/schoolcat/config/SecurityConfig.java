package com.ranchuanyin.schoolcat.config;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.User;
import com.ranchuanyin.schoolcat.enums.SchoolCatRedisCacheEnums;
import com.ranchuanyin.schoolcat.filter.JWTCalibrationFilter;
import com.ranchuanyin.schoolcat.mapper.CatAccountMapper;
import com.ranchuanyin.schoolcat.service.impl.AuthenticationServiceImpl;
import com.ranchuanyin.schoolcat.units.LoginAndRun;
import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.util.JwtUtil;
import com.ranchuanyin.schoolcat.util.RedisCache;
import com.ranchuanyin.schoolcat.vo.CatAccountVO;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private JWTCalibrationFilter jwtCalibrationFilter;
    @Resource
    private CatAccountMapper mapper;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisCache redisCache;

    //配置密码的加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    //设置记住密码的时候，token保存在一个表里面
    //这里使用JDBC来进行创建
    @Bean
    protected PersistentTokenRepository tokenRepository() {
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
                .requestMatchers("/cat/auth/**","/cat/comment/commentList").permitAll()//配置URL不需要登录就可以访问
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
                .logoutSuccessHandler(this::onLogOutOfLogin)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(3600 * 24 * 7)
                .and()
                .addFilterBefore(jwtCalibrationFilter, UsernamePasswordAuthenticationFilter.class)
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
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String remember = request.getParameter("remember");
        response.setContentType("application/json;charset=utf-8");
        User user = (User) authentication.getPrincipal();
        CatAccount catAccount = user.getCatAccount();

        String token = jwtUtil.createToken(catAccount, "true".equals(remember));
        CatAccountVO bean = BeanUtil.toBean(catAccount, CatAccountVO.class);
        bean.setJWT(token);
        if (Objects.isNull(redisCache.getCacheObject(SchoolCatRedisCacheEnums.NUMBER_OF_LOGINS))) {
            redisCache.setCacheObject(SchoolCatRedisCacheEnums.NUMBER_OF_LOGINS, "0");
        }

        String loginsAndTasks = SchoolCatRedisCacheEnums.USER_DAILY_ACTIONS + user.getCatAccount().getId().toString();
        LoginAndRun loginAndRun = redisCache.getCacheObject(loginsAndTasks);
        if (Objects.isNull(loginAndRun)) {
            loginAndRun = new LoginAndRun(user.getCatAccount().getId(), true, false);
            int i = mapper.IncreaseExperience(user.getCatAccount().getId(), 10);
            redisCache.setCacheObject(loginsAndTasks, loginAndRun, 24, TimeUnit.HOURS);
        }
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("登陆成功", bean)));
    }

    private void onLogOutOfLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(JSONObject.toJSONString(RestBean.success("欢迎再来哦")));
    }


    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401, exception.getMessage())));
    }
}
