package com.ranchuanyin.schoolcat.dto;

import com.ranchuanyin.schoolcat.domain.CatAccount;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SendMessageDto {
    private String fromUserId;
    private String toUserId;
    private String message;
}
