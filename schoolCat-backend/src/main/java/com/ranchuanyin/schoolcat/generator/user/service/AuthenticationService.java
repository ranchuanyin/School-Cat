package com.ranchuanyin.schoolcat.generator.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    String sendValidateEmail(String email, String sessionId, boolean hasAccount);


    String validateAndRegisterUser(String username, String password,String email,String code,String sessionId);

    String validateOnly(String email, String code, String sessionId);

    boolean resetPassword(String password, String email);
}
