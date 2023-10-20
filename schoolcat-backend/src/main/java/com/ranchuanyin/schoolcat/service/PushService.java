package com.ranchuanyin.schoolcat.service;

import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;

import java.util.List;

public interface PushService {
    /**
     * 推送给指定用户
     */
    Boolean pushMsgToOne(String formUserId, String toUserId, String msg);

    Boolean pushMsgToOne(String toUserId, List<ReceiveMessagesVo> list);

    /**
     * 推送给所有用户
     *
     * @param msg
     */
    void pushMsgToAll(String msg);
}

