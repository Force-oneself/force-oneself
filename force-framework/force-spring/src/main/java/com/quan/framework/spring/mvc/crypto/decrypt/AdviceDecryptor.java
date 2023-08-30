package com.quan.framework.spring.mvc.crypto.decrypt;

/**
 * <span>@RequestBody</span> 中解密算法实现
 *
 * @author Force-oneself
 * @date 2023-08-21
 * @see org.springframework.web.bind.annotation.RequestBody
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice
 */
public interface AdviceDecryptor {

    /**
     * 解密
     *
     * @param holder     上下文
     * @param ciphertext 密文
     * @return 明文
     */
    byte[] decryptor(DecryptAdviceHolder holder, byte[] ciphertext);
}
