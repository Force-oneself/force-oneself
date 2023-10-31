package com.quan.tools.exception;

/**
 * 限流异常
 *
 * @author Force-oneself
 * @date 2023-10-11
 */
public class RateLimitException extends BusinessException {

    public RateLimitException(int code, String message) {
        super(code, message);
    }

    public RateLimitException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
