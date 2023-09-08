package com.quan.boot.mvc.crypto.decrypt;

import org.springframework.http.HttpInputMessage;

import java.io.IOException;

/**
 * 针对不同类型请求的解密处理逻辑接口
 * <ul>
 *     <li>application/json</li>
 *     <li>application/json;charset=UTF-8</li>
 *     <li>application/xml</li>
 *     <li>text/html</li>
 *     <li>text/plain</li>
 * </ul>
 *
 * @author Force-oneself
 * @date 2023-08-18
 * @see org.springframework.http.MediaType
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
