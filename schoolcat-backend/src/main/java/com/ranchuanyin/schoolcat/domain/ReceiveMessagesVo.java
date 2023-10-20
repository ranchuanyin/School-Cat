package com.ranchuanyin.schoolcat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName(value = "receive_messages_vo")
public class ReceiveMessagesVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long toUserId;
    //信息来自
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long fromUserId;
    //信息
    private String message;

    private String messageDate;
}
