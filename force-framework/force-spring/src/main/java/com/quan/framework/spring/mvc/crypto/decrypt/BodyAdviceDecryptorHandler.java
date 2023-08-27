package com.quan.framework.spring.mvc.crypto.decrypt;

import org.springframework.lang.Nullable;

/**
 * @author Force-oneself
 * @date 2023-08-18
 */
public interface BodyAdviceDecryptorHandler {

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
     * 返回支持处理的解密器
     *
     * @param holder 上下文
     */
    @Nullable
    Decryptor decryptor(DecryptAdviceHolder holder);
}
