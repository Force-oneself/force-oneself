package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.request.CredentialRequest;

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
     * 认证
     *
     * @param request 认证请求
     * @return 认证用户信息
     * @throws AuthenticationException 认证异常
     */
    UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException;

    /**
     * 登出
     */
    void logout();

    /**
     * 获取当前用户
     *
     * @return 当前用户信息
     * @throws AuthenticationException 认证异常
     */
    UserPrincipal getCurrentUser() throws AuthenticationException;
}
