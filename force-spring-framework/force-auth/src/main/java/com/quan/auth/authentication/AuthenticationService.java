package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;

/**
 * AuthenticationService 接口定义了一系列与认证相关的操作，
 * 提供了认证、登出、获取当前用户以及管理认证监听器的功能。
 * 该接口是认证系统的核心部分，实现类需要根据具体的业务逻辑和安全策略来实现这些方法。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthenticationService {

    /**
     * 密码认证
     * 此方法用于根据用户提供的认证请求（包含用户名、密码等）进行认证操作。
     * 若认证成功，将返回认证成功的用户主体信息；若认证失败，将抛出 AuthenticationException 异常。
     *
     * @param request 认证请求，包含用户的认证凭证信息，如用户名、密码等
     * @return 认证成功的用户主体，包含用户的基本信息和权限信息
     * @throws AuthenticationException 认证失败时抛出的异常，包含认证失败的详细原因
     */
    <T extends CredentialRequest> UserPrincipal authenticate(T request) throws AuthenticationException;

    /**
     * 添加认证策略
     *
     * @param requestType 请求类型Class对象
     * @param strategy    对应的策略实现
     */
    <T extends CredentialRequest> void addStrategy(
            Class<T> requestType,
            AuthenticationStrategy<T> strategy);

    /**
     * 登出当前用户
     * 该方法用于将当前认证的用户登出系统。
     * 登出操作通常会清除用户的认证状态和相关的会话信息。
     */
    void logout();

    /**
     * 获取当前认证用户
     * 此方法用于获取当前已经认证的用户主体信息。
     * 若当前用户未认证，将抛出 AuthenticationException 异常。
     *
     * @return 用户主体信息，包含用户的基本信息和权限信息
     * @throws AuthenticationException 未认证时抛出的异常，表示当前用户没有有效的认证信息
     */
    UserPrincipal getCurrentUser() throws AuthenticationException;

    /**
     * 添加认证事件监听器
     * 该方法用于向认证服务中添加一个认证事件监听器。
     * 监听器可以监听认证成功、认证失败等事件，并执行相应的处理逻辑。
     *
     * @param listener 认证事件监听器，实现了 AuthenticationListener 接口的类实例
     */
    void addAuthenticationListener(AuthenticationListener listener);
}
