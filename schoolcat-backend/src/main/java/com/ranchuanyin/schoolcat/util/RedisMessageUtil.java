package com.ranchuanyin.schoolcat.util;

import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.enums.MessageByUserNameAndIdEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisMessageUtil {
    @Resource
    RedisCache redisCache;

    public void addOfflineMessage(Long userId, ReceiveMessagesVo messagesVo) {
        String RedisKey = String.format("%s:%s", userId, MessageByUserNameAndIdEnum.OFFLINE_MESSAGE_LIST);
        redisCache.setCacheList(RedisKey, messagesVo);
    }

    public List<ReceiveMessagesVo> getOfflineMessage(Long userId) {
        String RedisKey = String.format("%s:%s", userId, MessageByUserNameAndIdEnum.OFFLINE_MESSAGE_LIST);
        List<ReceiveMessagesVo> cacheList = redisCache.getCacheList(RedisKey);
        redisCache.deleteList(RedisKey);
        return cacheList;
    }
}
