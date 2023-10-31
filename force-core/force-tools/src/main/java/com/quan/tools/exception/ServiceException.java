package com.quan.tools.exception;

/**
 * 服务异常
 *
 * @author Force-oneself
 * @date 2023-10-11
 */
public class ServiceException extends BusinessException {

    public ServiceException(int code, String message) {
        super(code, message);
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
