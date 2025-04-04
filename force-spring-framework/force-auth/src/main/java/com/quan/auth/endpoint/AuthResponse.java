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
public class AuthResponse<T> {

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
    public static <T> AuthResponse<T> success(T data) {
        AuthResponse<T> response = new AuthResponse<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    /**
     * 创建错误响应
     */
    public static <T> AuthResponse<T> error(String message) {
        return error(500, message);
    }

    /**
     * 创建错误响应
     */
    public static <T> AuthResponse<T> error(int code, String message) {
        AuthResponse<T> response = new AuthResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }
} 