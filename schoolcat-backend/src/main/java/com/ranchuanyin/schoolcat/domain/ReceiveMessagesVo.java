package com.ranchuanyin.schoolcat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ReceiveMessagesVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long toUserId;
    //信息来自
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long fromUserId;

    private String username;

    private String avatar;
    //信息
    private String message;

    private String messageDate;
}
