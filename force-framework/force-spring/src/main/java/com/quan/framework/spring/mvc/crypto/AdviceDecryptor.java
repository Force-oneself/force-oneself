package com.quan.framework.spring.mvc.crypto;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
public interface AdviceDecryptor extends Decryptor {

    /**
     * 对解密请求是否支持解密处理
     *
     * @param inputMessage  输入消息信息
     * @param parameter     方法参数信息
     * @param targetType    目标类型
     * @param converterType 消息转换器
     * @return 是否支持
     */
    default boolean support(DecryptAdviceHolder holder) {
        return false;
    }
}
