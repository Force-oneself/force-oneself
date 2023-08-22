package com.quan.framework.spring.mvc.crypto.exception;

/**
 * @author Force-oneself
 * @date 2023-08-22
 */
public class DecryptException extends RuntimeException{

    public DecryptException() {
        super();
    }

    public DecryptException(String message) {
        super(message);
    }

    public DecryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecryptException(Throwable cause) {
        super(cause);
    }

    protected DecryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
