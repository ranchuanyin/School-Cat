package com.ranchuanyin.schoolcat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.ranchuanyin.schoolcat.config.NettyConfig;
import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.service.PushService;
import com.ranchuanyin.schoolcat.util.RedisMessageUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PushServiceImpl implements PushService {
    @Resource
    RedisMessageUtil redisMessageUtil;


    @Override
    public Boolean pushMsgToOne(String formUserId, String toUserId, String msg, String username, String avatar) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        List<ReceiveMessagesVo> list = new ArrayList<>();
        Channel channel = userChannelMap.get(toUserId);
        ReceiveMessagesVo vo = new ReceiveMessagesVo();
        vo.setMessage(msg);
        vo.setUsername(username);
        vo.setAvatar(avatar);
        vo.setToUserId(Long.valueOf(toUserId));
        vo.setFromUserId(Long.valueOf(formUserId));
        vo.setMessageDate(DateUtil.now());
        list.add(vo);
        if (Objects.isNull(channel)) {
            redisMessageUtil.addOfflineMessage(Long.valueOf(toUserId), vo);
            return false;
        }
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(list)));
        return true;
    }

    @Override
    public Boolean pushMsgToOne(String toUserId, List<ReceiveMessagesVo> list) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(toUserId);
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(list)));
        return true;
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}

