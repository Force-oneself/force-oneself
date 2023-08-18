package com.quan.boot.mvc.log;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
public enum LoggerLevel {

    REQUEST_BASIC(1),
    REQUEST_HEADER(2),
    REQUEST_BODY(4),
    RESPONSE_BASIC(8),
    RESPONSE_HEADER(16),
    RESPONSE_BODY(32),
    RESPONSE_ERROR(64)
    ;

    private final int code;

    LoggerLevel(int code) {
        this.code = code;
    }

    public static boolean enable(int level, LoggerLevel loggerLevel) {
        if (loggerLevel == null) {
            return false;
        }
        return (loggerLevel.getCode() & level) > 0;
    }

    public int getCode() {
        return code;
    }
}
