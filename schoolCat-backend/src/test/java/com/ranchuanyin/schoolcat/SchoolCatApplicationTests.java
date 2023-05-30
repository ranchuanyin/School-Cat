package com.ranchuanyin.schoolcat;

import com.ranchuanyin.schoolcat.common.toolclass.RedisCache;
import com.ranchuanyin.schoolcat.generator.user.domain.CatAccount;
import com.ranchuanyin.schoolcat.generator.user.mapper.CatAccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SchoolCatApplicationTests {

    @Autowired
    RedisCache redisCache;

    @Autowired
    private CatAccountMapper catAccountMapper;

    @Test
    void contextLoads() {
        List<CatAccount> list = redisCache.getCacheList("list",CatAccount.class);
        list.forEach(System.out::println);
    }

    @Test
    void selectAll() {
        List<CatAccount> catAccountList = new ArrayList();
        CatAccount catAccount = new CatAccount();
        catAccount.setId(1L);
        catAccount.setUsername("张三");
        catAccount.setEmail("94415164@qq.com");
        catAccountList.add(catAccount);
        catAccountList.add(catAccount);
        catAccountList.add(catAccount);
        redisCache.setCacheList("list",catAccountList);
    }

}
