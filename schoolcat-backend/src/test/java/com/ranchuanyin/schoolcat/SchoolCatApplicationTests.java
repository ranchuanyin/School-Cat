package com.ranchuanyin.schoolcat;

import com.ranchuanyin.schoolcat.domain.vo.CatAccountVO;
import com.ranchuanyin.schoolcat.mapper.CatAccountMapper;
import com.ranchuanyin.schoolcat.units.LoginAndRun;
import com.ranchuanyin.schoolcat.units.OSSUnit;
import com.ranchuanyin.schoolcat.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SchoolCatApplicationTests {

    @Autowired
    RedisCache redisCache;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private CatAccountMapper catAccountMapper;
    @Autowired
    private OSSUnit oSSUnit;

    @Test
    void contextLoads() {

        LoginAndRun userDailyActions2 = redisCache.getCacheObject("USER_DAILY_ACTIONS2");
        System.out.println(userDailyActions2);

    }

    @Test
    void selectAll() {
        redisTemplate.opsForZSet().incrementScore("catScore", CatAccountVO.builder().build(), 1);
    }

}
