package com.ranchuanyin.schoolcat.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SendMessageDto {
    private String fromUserId;
    private String toUserId;
    private String message;
    private String username;
    private String avatar;
}
