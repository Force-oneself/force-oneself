package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;

/**
 * AuthenticationStrategy 接口定义了认证策略的规范，它允许使用不同类型的认证请求进行认证操作。
 * 该接口为不同的认证场景提供了可扩展的认证策略，通过泛型 T 可以支持多种类型的凭证请求。
 *
 * @param <T> 实现了 CredentialRequest 接口的具体认证请求类型
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthenticationStrategy<T extends CredentialRequest> {

    /**
     * 根据传入的认证请求进行认证操作。
     * 此方法会根据具体的认证策略对请求进行验证，若认证成功，将返回认证成功的用户主体信息；
     * 若认证失败，将抛出 AuthenticationException 异常。
     *
     * @param request 具体的认证请求，包含用户的认证凭证信息，如用户名、密码等
     * @return 认证成功的用户主体，包含用户的基本信息和权限信息
     * @throws AuthenticationException 认证失败时抛出的异常，包含认证失败的详细原因
     */
    UserPrincipal authenticate(T request) throws AuthenticationException;
}
