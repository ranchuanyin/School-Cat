package com.schoolcat.resp;

import com.schoolcat.entity.Adoptions;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResp<T> implements Serializable {
    /**
     * 响应状态码:1成功，0失败
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应成功直接调用此方法，传入需要放回的数据即可（data）
     */
    public static <T> CommonResp<T> success(T object) {
        CommonResp<T> r = new CommonResp<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    /**
     * 响应失败直接调用此方法，传入需要响应的提示信息即可（msg）
     */
    public static <T> CommonResp<T> error(String msg) {
        CommonResp r = new CommonResp();
        r.msg = msg;
        r.code = 0;
        return r;
    }


}
