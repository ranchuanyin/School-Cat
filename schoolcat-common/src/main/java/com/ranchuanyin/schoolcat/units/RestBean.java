package com.ranchuanyin.schoolcat.units;

import com.ranchuanyin.schoolcat.vo.PageVO;
import lombok.Data;

@Data
public class RestBean<T> {
    private int status;
    private String message;
    private boolean success;
    private T data;
    private PageVO page;


    private RestBean(int status, String message, boolean success, T data, PageVO page) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.data = data;
        this.page = page;
    }

    private RestBean(int status, String message, boolean success, T data) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <T> RestBean<T> success() {
        return new RestBean<>(200, null, true, null);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, null, true, data);
    }

    public static <T> RestBean<T> success(String message) {
        return new RestBean<>(200, message, true, null);
    }

    public static <T> RestBean<T> success(String message, T data) {
        return new RestBean<>(200, message, true, data);
    }

    public static <T> RestBean<T> success(String message, T data, PageVO page) {
        return new RestBean<>(200, message, true, data, page);
    }

    public static <T> RestBean<T> success(T data, PageVO page) {
        return new RestBean<>(200, null, true, data, page);
    }


    public static <T> RestBean<T> failure(int status) {
        return new RestBean<>(status, null, false, null);
    }

    public static <T> RestBean<T> failure(int status, T data) {
        return new RestBean<>(status, null, false, data);
    }

    public static <T> RestBean<T> failure(int status, String message, T data) {
        return new RestBean<>(status, message, false, data);
    }

    public static <T> RestBean<T> failure(int status, String message) {
        return new RestBean<>(status, message, false, null);
    }


}
