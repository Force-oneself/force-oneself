package com.quan.framework.spring.mvc.crypto.encrypt;

/**
 * @author Force-oneself
 * @date 2023-08-29
 */
public interface BodyAdviceEncryptorHandler {

    /**
     * 对加密请求是否支持加密处理
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
     * @param holder 上下文
     * @return 输入消息
     */
    Object encrypt(EncryptAdviceHolder holder);
}
