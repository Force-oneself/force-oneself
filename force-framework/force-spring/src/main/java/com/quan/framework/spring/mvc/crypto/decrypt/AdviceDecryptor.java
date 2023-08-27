package com.quan.framework.spring.mvc.crypto.decrypt;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
public interface AdviceDecryptor {

    /**
     * 对解密请求是否支持解密处理
     *
     * @param holder 上下文
     * @return 是否支持
     */
    default boolean support(DecryptAdviceHolder holder) {
        return false;
    }

    /**
     * 解密
     *
     * @param holder     上下文
     * @param ciphertext 密文
     * @return 明文
     */
    byte[] decryptor(DecryptAdviceHolder holder, byte[] ciphertext);
}
