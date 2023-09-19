package com.ranchuanyin.schoolcat.service;

public interface PushService {
    /**
     * 推送给指定用户
     */
    void pushMsgToOne(String formUserId,String toUserId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg
     */
    void pushMsgToAll(String msg);
}

