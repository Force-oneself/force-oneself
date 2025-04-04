package com.quan.auth.token;

/**
 * TokenException 类是一个自定义异常类，用于表示在处理令牌相关操作时可能出现的异常情况。
 * 当令牌生成、验证、解析等操作失败时，可以抛出此异常，以便调用者进行相应的处理。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public class TokenException extends Exception {

    /**
     * 无参构造函数，创建一个默认的 TokenException 实例。
     */
    public TokenException() {
        super();
    }

    /**
     * 带有错误消息的构造函数，创建一个包含指定错误消息的 TokenException 实例。
     *
     * @param message 描述异常的详细信息
     */
    public TokenException(String message) {
        super(message);
    }

    /**
     * 带有错误消息和原始异常的构造函数，创建一个包含指定错误消息和原始异常的 TokenException 实例。
     *
     * @param message 描述异常的详细信息
     * @param cause   导致此异常的原始异常
     */
    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 带有原始异常的构造函数，创建一个包含指定原始异常的 TokenException 实例。
     *
     * @param cause 导致此异常的原始异常
     */
    public TokenException(Throwable cause) {
        super(cause);
    }
}
