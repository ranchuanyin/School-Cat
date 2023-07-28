package com.schoolcat.controller;

import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}/{code}")
    public CommonResp<String> update(@PathVariable Long id, @PathVariable Integer code) {
        CommonResp<String> resp = userService.updateUserField(id,code);
        return resp;
    }
}
