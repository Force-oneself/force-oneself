package com.quan.framework.spring.mvc.crypto.decrypt;

import org.springframework.http.HttpInputMessage;

import java.io.IOException;

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
     * 解密成消息
     *
     * @param holder 上下文
     * @return 输入消息
     */
    HttpInputMessage decrypt(DecryptAdviceHolder holder) throws IOException;
}
