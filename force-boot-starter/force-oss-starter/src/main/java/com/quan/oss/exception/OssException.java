package com.quan.oss.exception;

/**
 * Oss 异常
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public class OssException extends RuntimeException {

    public OssException(String message) {
        super(message);
    }
}
