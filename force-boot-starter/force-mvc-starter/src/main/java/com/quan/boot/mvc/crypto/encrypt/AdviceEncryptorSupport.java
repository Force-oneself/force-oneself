package com.quan.boot.mvc.crypto.encrypt;

/**
 * 针对自定义注等解加密实现
 *
 * @author Force-oneself
 * @date 2023-08-30
 */
public interface AdviceEncryptorSupport extends AdviceEncryptor {

    /**
     * 对加密密请求是否支持加密处理
     *
     * @param holder 上下文
     * @return 是否支持
     */
    default boolean support(EncryptAdviceHolder holder) {
        return false;
    }
}
