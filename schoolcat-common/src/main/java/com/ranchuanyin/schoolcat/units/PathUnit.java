package com.ranchuanyin.schoolcat.units;

import cn.hutool.core.util.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PathUnit {
    public static String getFilePath(String oldName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        int index = oldName.lastIndexOf(".");
        String datePath = sdf.format(new Date());
        String name = IdUtil.randomUUID();
        String fileType = oldName.substring(index);
        return datePath + name + fileType;
    }
}
