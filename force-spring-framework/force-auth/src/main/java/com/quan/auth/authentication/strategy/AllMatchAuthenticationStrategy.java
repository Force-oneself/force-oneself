package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.request.CredentialRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部匹配认证策略，所有策略都需要通过
 */
public class AllMatchAuthenticationStrategy implements AuthenticationStrategy {

    private final List<AuthenticationStrategy> strategies = new ArrayList<>();

    /**
     * 添加认证策略
     */
    public AllMatchAuthenticationStrategy addStrategy(AuthenticationStrategy strategy) {
        strategies.add(strategy);
        return this;
    }

    @Override
    public boolean supports(CredentialRequest request) {
        return strategies.stream().allMatch(strategy -> strategy.supports(request));
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        UserPrincipal principal = null;
        for (AuthenticationStrategy strategy : strategies) {
            principal = strategy.authenticate(request);
            if (principal == null) {
                throw new AuthenticationException("Authentication failed");
            }
        }
        return principal;
    }
} 