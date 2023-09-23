package com.quan.tools.exception;

/**
 * @author Force-oneself
 * @date 2023-09-22
 */
public class BusinessException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766919L;

    private String code;

    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
