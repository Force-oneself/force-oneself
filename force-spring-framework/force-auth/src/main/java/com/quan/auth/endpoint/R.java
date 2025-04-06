package com.quan.auth.endpoint;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用响应对象
 *
 * @param <T> 响应数据类型
 */
@Getter
@Setter
public class R<T> {

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 创建成功响应
     */
    public static <T> R<T> success(T data) {
        R<T> response = new R<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    /**
     * 创建错误响应
     */
    public static <T> R<T> error(String message) {
        return error(500, message);
    }

    /**
     * 创建错误响应
     */
    public static <T> R<T> error(int code, String message) {
        R<T> response = new R<>();
        response.setCode(code);
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }
} 