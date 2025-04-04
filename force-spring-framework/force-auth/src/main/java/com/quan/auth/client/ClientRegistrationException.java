package com.quan.auth.client;

/**
 * 注册客户端异常
 * 当在客户端注册过程中出现问题时，抛出此异常。
 * 该异常用于明确表示客户端注册操作失败的情况，可携带具体的错误信息。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public class ClientRegistrationException extends Exception {
    /**
     * 无参构造函数，创建一个默认的 ClientRegistrationException 实例。
     */
    public ClientRegistrationException() {
        super();
    }

    /**
     * 带有错误消息的构造函数，创建一个包含指定错误消息的 ClientRegistrationException 实例。
     *
     * @param message 描述异常的详细信息
     */
    public ClientRegistrationException(String message) {
        super(message);
    }

    /**
     * 带有错误消息和原始异常的构造函数，创建一个包含指定错误消息和原始异常的 ClientRegistrationException 实例。
     *
     * @param message 描述异常的详细信息
     * @param cause   导致此异常的原始异常
     */
    public ClientRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 带有原始异常的构造函数，创建一个包含指定原始异常的 ClientRegistrationException 实例。
     *
     * @param cause 导致此异常的原始异常
     */
    public ClientRegistrationException(Throwable cause) {
        super(cause);
    }
}
