package com.quan.tools.exception;

/**
 * @author Force-oneself
 * @date 2023-09-22
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -7034897190745766919L;

    protected int code;

    public BusinessException(String message) {
        this(500, message);
    }

    public BusinessException(int code, String message) {
        this(code, message, null);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
