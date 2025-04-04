package com.quan.auth.security;

/**
 * 密码策略异常类，继承自 Exception 类。
 * 当用户输入的密码不符合预先设定的密码策略时，抛出此异常。
 * 例如密码长度不足、未包含特定字符类型等情况。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public class PasswordPolicyException extends Exception {

    /**
     * 无参构造函数，创建一个默认的 PasswordPolicyException 实例。
     */
    public PasswordPolicyException() {
        super();
    }

    /**
     * 带有错误消息的构造函数，创建一个包含指定错误消息的 PasswordPolicyException 实例。
     *
     * @param message 描述异常的详细信息，如具体的密码策略不符合点
     */
    public PasswordPolicyException(String message) {
        super(message);
    }

    /**
     * 带有错误消息和原始异常的构造函数，创建一个包含指定错误消息和原始异常的 PasswordPolicyException 实例。
     *
     * @param message 描述异常的详细信息，如具体的密码策略不符合点
     * @param cause 导致此异常的原始异常
     */
    public PasswordPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 带有原始异常的构造函数，创建一个包含指定原始异常的 PasswordPolicyException 实例。
     *
     * @param cause 导致此异常的原始异常
     */
    public PasswordPolicyException(Throwable cause) {
        super(cause);
    }
}
