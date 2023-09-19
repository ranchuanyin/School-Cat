package com.ranchuanyin.schoolcat.service.impl;

import com.alibaba.fastjson.JSON;
import com.ranchuanyin.schoolcat.config.NettyConfig;
import com.ranchuanyin.schoolcat.service.PushService;
import com.ranchuanyin.schoolcat.vo.ReceiveMessagesVo;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class PushServiceImpl implements PushService {

    @Override
    public void pushMsgToOne(String formUserId,String toUserId,String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(toUserId);
        ReceiveMessagesVo vo = ReceiveMessagesVo.builder().message(msg).fromUserId(formUserId).build();
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(vo)));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}

