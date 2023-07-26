package com.schoolcat.resp;

import lombok.Data;

@Data
public class CommonResp<T> {
    //业务上的成功与失败
    private Boolean success = true;

    //返回信息
    private String message;

    //返回一个泛型数据 自定义类型
    private T content;
}
