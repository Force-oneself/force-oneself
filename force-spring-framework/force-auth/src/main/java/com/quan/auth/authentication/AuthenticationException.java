package com.quan.auth.authentication;

/**
 * 认证异常类，继承自 Exception。
 * 当在认证过程中出现问题时，会抛出该异常，用于表示认证操作失败。
 * 例如，用户凭证无效、认证过程中出现系统错误等情况。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public class AuthenticationException extends Exception {

    /**
     * 无参构造函数，创建一个默认的 AuthenticationException 实例。
     */
    public AuthenticationException() {
        super();
    }

    /**
     * 带有错误消息的构造函数，创建一个包含指定错误消息的 AuthenticationException 实例。
     *
     * @param message 描述异常的详细信息
     */
    public AuthenticationException(String message) {
        super(message);
    }

    /**
     * 带有错误消息和原始异常的构造函数，创建一个包含指定错误消息和原始异常的 AuthenticationException 实例。
     *
     * @param message 描述异常的详细信息
     * @param cause   导致此异常的原始异常
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 带有原始异常的构造函数，创建一个包含指定原始异常的 AuthenticationException 实例。
     *
     * @param cause 导致此异常的原始异常
     */
    public AuthenticationException(Throwable cause) {
        super(cause);
    }
}
