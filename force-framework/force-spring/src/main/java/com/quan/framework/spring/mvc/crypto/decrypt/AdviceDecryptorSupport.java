package com.quan.framework.spring.mvc.crypto.decrypt;

/**
 * 针对自定义注等解解密实现
 *
 * @author Force-oneself
 * @date 2023-08-30
 * @see com.quan.framework.spring.mvc.crypto.annotation.RSADecrypt
 */
public interface AdviceDecryptorSupport extends AdviceDecryptor {

    /**
     * 对解密请求是否支持解密处理
     *
     * @param holder 上下文
     * @return 是否支持
     */
    default boolean support(DecryptAdviceHolder holder) {
        return false;
    }
}
