package com.ranchuanyin.schoolcat.config;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //experience的初始值添加为0
        setFieldValByName("experience", 0, metaObject);
        setFieldValByName("insertTime", DateTime.now(),metaObject);
        setFieldValByName("createTime",DateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
