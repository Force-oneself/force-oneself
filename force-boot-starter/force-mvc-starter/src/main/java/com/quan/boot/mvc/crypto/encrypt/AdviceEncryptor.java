package com.quan.boot.mvc.crypto.encrypt;

/**
 * 加密算法实现
 *
 * @author Force-oneself
 * @date 2023-08-27
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 */
public interface AdviceEncryptor {

    /**
     * 加密
     *
     * @param holder     上下文
     * @param ciphertext 明文
     * @return 密文
     */
    byte[] encrypt(EncryptAdviceHolder holder, byte[] ciphertext);
}
