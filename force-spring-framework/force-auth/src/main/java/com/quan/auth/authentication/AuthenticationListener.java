package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.token.Token;

/**
 * 认证监听器接口，用于监听认证过程中的相关事件。
 * 实现该接口的类可以在认证成功、失败等关键节点执行特定的逻辑，
 * 比如记录日志、更新用户状态等。
 * 此接口为认证流程提供了一种可扩展的机制，方便在不同的认证场景下添加额外的处理逻辑。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthenticationListener {

    /**
     * 认证成功事件
     *
     * @param request   认证请求
     * @param principal 认证用户信息
     */
    void onAuthenticationSuccess(CredentialRequest request, UserPrincipal principal);

    /**
     * 认证失败事件
     *
     * @param request   认证请求
     * @param exception 认证异常
     */
    void onAuthenticationFailure(CredentialRequest request, Exception exception);

}
