package com.quan.boot.mvc.crypto.encrypt;

/**
 * @author Force-oneself
 * @date 2023-08-23
 */
@FunctionalInterface
public interface EncryptHandler {

    /**
     * 返回支持处理的加密器
     *
     * @param holder 上下文
     */
    byte[] encrypt(EncryptAdviceHolder holder, byte[] data);
}
