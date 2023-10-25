package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.service.AuthenticationService;
import com.ranchuanyin.schoolcat.service.PushService;
import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.util.RedisCache;
import com.ranchuanyin.schoolcat.util.RedisMessageUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Validated
@RestController
@RequestMapping("/cat/auth")
public class AuthenticationController {
    final
    AuthenticationService authenticationService;
    private final String NUMBER = "^\\d{6}$";
    private final String USER_NAME = "^[a-zA-Z0-9一-龥]+$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    @Resource
    private RedisCache redisCache;

    @Resource
    RedisMessageUtil redisMessageUtil;
    @Resource
    PushService pushService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/message/{id}")
    public void message(@PathVariable String id) throws IOException, TimeoutException, InterruptedException {
        List<ReceiveMessagesVo> receiveMessagesVos = redisMessageUtil.getOfflineMessage(Long.valueOf(id));
        Thread.sleep(2000);
        pushService.pushMsgToOne(id, receiveMessagesVos);
    }

    @PostMapping("/del-redis")
    public RestBean<String> delRedisUser(Long id) {
        redisCache.deleteObject("login:" + id);
        return RestBean.success("要再回来哦！");
    }

    @PostMapping("/valid-register-email")
    public RestBean<String> validateEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email, HttpSession session) {
        if (authenticationService.sendValidateEmail(email, session.getId(), false) == null)
            return RestBean.success("邮件已发送，请稍后");
        else
            return RestBean.failure(400, authenticationService.sendValidateEmail(email, session.getId(), false));
    }

    @PostMapping("/valid-reset-email")
    public RestBean<String> validateResetEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                               HttpSession session) {
        String s = authenticationService.sendValidateEmail(email, session.getId(), true);
        if (s == null)
            return RestBean.success("邮件已发送，请稍后");
        else
            return RestBean.failure(400, s);
    }

    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USER_NAME) @Length(min = 2, max = 8) @RequestParam("username") String username,
                                         @Length(min = 6, max = 20) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) @Pattern(regexp = NUMBER) @RequestParam("code") String code,
                                         HttpSession session
    ) {
        String message = authenticationService.validateAndRegisterUser(username, password, email, code, session.getId());
        if (message == null)
            return RestBean.success("注册成功");
        else
            return RestBean.failure(400, message);
    }

    @PostMapping("/do-reset")
    public RestBean<String> resetPassword(@Length(min = 6, max = 16) @RequestParam("password") String password,
                                          HttpSession session) {
        String email = (String) session.getAttribute("reset-password");
        if (email == null) {
            return RestBean.failure(401, "请先完成邮箱验证");
        } else if (authenticationService.resetPassword(password, email)) {
            session.removeAttribute("reset-password");
            return RestBean.success("密码重置成功");
        } else {
            return RestBean.failure(500, "内部错误，请联系管理员");
        }
    }

    @PostMapping("/start-reset")
    public RestBean<String> startReset(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                       @Length(min = 6, max = 6) @RequestParam("code") String code,
                                       HttpSession session) {
        String s = authenticationService.validateOnly(email, code, session.getId());
        if (s == null) {
            session.setAttribute("reset-password", email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, s);
        }
    }
}
