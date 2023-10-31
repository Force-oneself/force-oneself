package com.quan.tools.exception;

/**
 * 非法参数异常
 *
 * @author Force-oneself
 * @date 2023-10-12
 */
public class ArgumentException extends BusinessException {

    public ArgumentException(int code, String message) {
        super(code, message);
    }

    public ArgumentException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    /**
     * 不打印异常堆栈
     *
     * @return /
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
