package com.quan.framework.spring.mvc.crypto.encrypt;

/**
 * @author Force-oneself
 * @date 2023-08-27
 */
public interface AdviceEncryptor {

    /**
     * 对加密密请求是否支持加密处理
     *
     * @param holder 上下文
     * @return 是否支持
     */
    default boolean support(EncryptAdviceHolder holder) {
        return false;
    }

    /**
     * 加密
     *
     * @param holder     上下文
     * @param ciphertext 明文
     * @return 密文
     */
    byte[] decryptor(EncryptAdviceHolder holder, byte[] ciphertext);
}
