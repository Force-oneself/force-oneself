package com.quan.boot.mvc.log;

/**
 * 日志级别枚举
 *
 * @author Force-oneself
 * @date 2023-03-01
 */
public enum LoggerLevel {

    /**
     * 请求基础信息
     */
    REQUEST_BASIC(1),

    /**
     * 请求头信息
     */
    REQUEST_HEADER(2),

    /**
     * 请求体信息
     */
    REQUEST_BODY(4),

    /**
     * 响应基础信息
     */
    RESPONSE_BASIC(8),

    /**
     * 响应头信息
     */
    RESPONSE_HEADER(16),

    /**
     * 响应体信息
     */
    RESPONSE_BODY(32),

    /**
     * 异常信息
     */
    RESPONSE_ERROR(64);

    private final int code;

    LoggerLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    /**
     * 是否开启该日志级别
     *
     * @param level 日志级别
     * @return 是否开启
     */
    public boolean isEnable(int level) {
        return (this.code & level) > 0;
    }

    public static boolean enable(int level, LoggerLevel loggerLevel) {
        return loggerLevel != null && (loggerLevel.getCode() & level) > 0;
    }
}
