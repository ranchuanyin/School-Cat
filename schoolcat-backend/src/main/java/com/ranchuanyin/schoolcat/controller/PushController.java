package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.dto.SendMessageDto;
import com.ranchuanyin.schoolcat.service.PushService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat/message")
public class PushController {
    @Resource
    private PushService pushService;

    /**
     * 推送给所有用户
     * @param msg
     */
    @PostMapping("/pushAll")
    public void pushToAll(@RequestParam("msg") String msg){
        pushService.pushMsgToAll(msg);
    }
    /**
     * 推送给指定用户
     */
    @PostMapping("/pushOne")
    public void pushMsgToOne(@RequestBody SendMessageDto sendMessageDto){
        pushService.pushMsgToOne(sendMessageDto.getFromUserId(),sendMessageDto.getToUserId(),sendMessageDto.getMessage());
    }

}
