package com.ranchuanyin.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.User;
import com.ranchuanyin.schoolcat.mapper.CatAccountMapper;
import com.ranchuanyin.schoolcat.service.AuthenticationService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final CatAccountMapper catAccountMapper;
    @Value("${spring.mail.username}")
    String fromMail;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    MailSender mailSender;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public AuthenticationServiceImpl(CatAccountMapper catAccountMapper) {
        this.catAccountMapper = catAccountMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        LambdaQueryWrapper<CatAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CatAccount::getUsername, username).or().eq(CatAccount::getEmail, username);
        CatAccount catAccount = catAccountMapper.selectOne(queryWrapper);
        if (Objects.isNull(catAccount)) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return new User(catAccount);
    }

    @Override
    public String sendValidateEmail(String email, String sessionId, boolean hasAccount) {
        String key = email + ":" + sessionId + hasAccount;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            Long expire = Optional.ofNullable(stringRedisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 240)
                return "请求频繁，稍后再试";
        }
        CatAccount catAccount = catAccountMapper.selectOne(new LambdaQueryWrapper<CatAccount>().eq(CatAccount::getEmail, email));
        if (hasAccount && catAccount == null) return "没有此邮件地址的账户";
        if (!hasAccount && catAccount != null) return "此邮箱已被其他用户注册";
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(email);
        message.setSubject("您的验证码邮件");
        message.setText("验证码：" + code + "  " + "验证码5分钟内有效");
        try {
            mailSender.send(message);
            stringRedisTemplate.opsForValue().set(key, String.valueOf(code), 5, TimeUnit.MINUTES);
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败，请检查邮箱是否正确";
        }
    }

    @Override
    public String validateAndRegisterUser(String username, String password, String email, String code, String sessionId) {
        String key = email + ":" + sessionId + "false";
        LambdaQueryWrapper<CatAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CatAccount::getUsername, username);
        CatAccount catAccount1 = catAccountMapper.selectOne(wrapper);
        if (Objects.nonNull(catAccount1)) {
            return "用户名已经存在";
        }
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            String s = stringRedisTemplate.opsForValue().get(key);
            if (s == null)
                return "验证码失效，重新验证";
            if (s.equals(code)) {
                password = bCryptPasswordEncoder.encode(password);
                CatAccount catAccount = new CatAccount();
                catAccount.setUsername(username);
                catAccount.setPassword(password);
                catAccount.setEmail(email);
                if (catAccountMapper.insert(catAccount) > 0) {
                    return null;
                } else {
                    return "内部错误，请联系管理员";
                }

            } else {
                return "验证码错误";
            }
        } else {
            return "请先完成邮箱认证";
        }
    }

    @Override
    public String validateOnly(String email, String code, String sessionId) {
        String key = email + ":" + sessionId + "true";
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
            String s = stringRedisTemplate.opsForValue().get(key);
            if (s == null) return "验证码失效，请重新请求";
            if (s.equals(code)) {
                stringRedisTemplate.delete(key);
                return null;
            } else {
                return "验证码错误，请检查后再提交";
            }
        } else {
            return "请先请求一封验证码邮件";
        }
    }


    @Override
    public boolean resetPassword(String password, String email) {
        password = bCryptPasswordEncoder.encode(password);
        return catAccountMapper.resetPasswordByEmail(password, email) > 0;
    }
}
