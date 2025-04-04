package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.request.CredentialRequest;

/**
 * 认证策略接口，定义认证策略的规范。
 * 不同的认证方式（如密码、短信、第三方等）实现该接口来提供具体的认证逻辑。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthenticationStrategy {

    /**
     * 判断是否支持该认证请求
     *
     * @param request 认证请求
     * @return 是否支持
     */
    boolean supports(CredentialRequest request);

    /**
     * 认证处理
     *
     * @param request 认证请求
     * @return 认证用户信息
     * @throws AuthenticationException 认证异常
     */
    UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException;
}
