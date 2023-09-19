package com.ranchuanyin.schoolcat.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReceiveMessagesVo {
    //信息来自
    private String fromUserId;
    //信息
    private String message;
}
