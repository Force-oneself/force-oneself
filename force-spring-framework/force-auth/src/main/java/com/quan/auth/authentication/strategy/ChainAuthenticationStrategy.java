package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.AuthenticationStrategy;
import com.quan.auth.authentication.request.CredentialRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 链式认证策略，按顺序执行认证策略，直到找到匹配的策略
 */
public class ChainAuthenticationStrategy implements AuthenticationStrategy {

    private final List<AuthenticationStrategy> strategies = new ArrayList<>();

    /**
     * 添加认证策略
     */
    public ChainAuthenticationStrategy addStrategy(AuthenticationStrategy strategy) {
        strategies.add(strategy);
        return this;
    }

    @Override
    public boolean supports(CredentialRequest request) {
        return strategies.stream().anyMatch(strategy -> strategy.supports(request));
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        for (AuthenticationStrategy strategy : strategies) {
            if (strategy.supports(request)) {
                return strategy.authenticate(request);
            }
        }
        throw new AuthenticationException("No suitable authentication strategy found");
    }
} 