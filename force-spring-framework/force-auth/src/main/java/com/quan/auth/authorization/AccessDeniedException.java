package com.quan.auth.authorization;

/**
 * 访问被拒绝异常
 * 当用户试图访问受保护的资源，但没有足够的权限时，抛出此异常。
 * 该异常继承自 Exception 类，用于在授权过程中明确表示访问被拒绝的情况。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public class AccessDeniedException extends Exception {
    /**
     * 无参构造函数，创建一个默认的 AccessDeniedException 实例。
     */
    public AccessDeniedException() {
        super();
    }

    /**
     * 带有错误消息的构造函数，创建一个包含指定错误消息的 AccessDeniedException 实例。
     *
     * @param message 描述异常的详细信息
     */
    public AccessDeniedException(String message) {
        super(message);
    }

    /**
     * 带有错误消息和原始异常的构造函数，创建一个包含指定错误消息和原始异常的 AccessDeniedException 实例。
     *
     * @param message 描述异常的详细信息
     * @param cause   导致此异常的原始异常
     */
    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 带有原始异常的构造函数，创建一个包含指定原始异常的 AccessDeniedException 实例。
     *
     * @param cause 导致此异常的原始异常
     */
    public AccessDeniedException(Throwable cause) {
        super(cause);
    }
}
