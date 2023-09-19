package com.ranchuanyin.schoolcat.config;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;

public class NettyConfig {
    /**
     * -- GETTER --
     *  获取channel组
     */
    @Getter
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存放用户与Chanel的对应信息，用于给指定用户发送消息
     * -- GETTER --
     *  获取用户channel map
     *
     * @return

     */
    @Getter
    private static ConcurrentHashMap<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    private NettyConfig() {
    }

}
