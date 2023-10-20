package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.service.ReceiveMessagesVoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat/message")
public class MessageController {
    @Resource
    ReceiveMessagesVoService receiveMessagesVoService;


}
