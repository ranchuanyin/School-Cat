package com.ranchuanyin.schoolcat;

import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.util.RedisCache;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SchoolCatApplicationTest {
    @Resource
    RedisCache redisCache;

    @Test
    public void test() {
        List<ReceiveMessagesVo> a = new ArrayList<>();
        ReceiveMessagesVo b = new ReceiveMessagesVo();
        ReceiveMessagesVo c = new ReceiveMessagesVo();
        ReceiveMessagesVo d = new ReceiveMessagesVo();
        a.add(b);
        a.add(c);
        a.add(d);
        redisCache.setCacheList("111", a);
    }

    @Test
    public void test2() {
        List<ReceiveMessagesVo> cacheList = redisCache.getCacheList("111");
        System.out.println(cacheList.toString());
    }

    @Test
    public void test3() {
        ReceiveMessagesVo b = new ReceiveMessagesVo();

        redisCache.setCacheList("111", b);
    }
}
