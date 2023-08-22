package com.quan.framework.spring.mvc.crypto.encrypt;

/**
 * @author Force-oneself
 * @date 2023-08-17
 */
@FunctionalInterface
public interface Encryptor {

    /**
     * 加密
     *
     * @param plaintext 明文
     * @return 密文
     */
    byte[] encryption(byte[] plaintext);
}
