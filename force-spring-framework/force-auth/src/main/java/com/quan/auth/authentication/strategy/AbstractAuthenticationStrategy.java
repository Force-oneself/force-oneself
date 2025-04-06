package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.token.Token;

/**
 * 抽象认证策略基类
 *
 * @param <T> 认证请求类型
 */
public abstract class AbstractAuthenticationStrategy<T extends CredentialRequest> implements AuthenticationStrategy {

    private final Class<T> supportType;

    protected AbstractAuthenticationStrategy(Class<T> supportType) {
        this.supportType = supportType;
    }

    @Override
    public final boolean supports(CredentialRequest request) {
        return supportType.isInstance(request);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        return doAuthenticate((T) request);
    }

    /**
     * 执行具体的认证逻辑
     *
     * @param request 认证请求
     * @return 认证用户信息
     * @throws AuthenticationException 认证异常
     */
    protected abstract UserPrincipal doAuthenticate(T request) throws AuthenticationException;
} 