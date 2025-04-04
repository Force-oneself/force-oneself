package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.AuthenticationStrategy;
import com.quan.auth.authentication.request.CredentialRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 任一匹配认证策略，任一策略通过即可
 */
public class AnyMatchAuthenticationStrategy implements AuthenticationStrategy {

    private final List<AuthenticationStrategy> strategies = new ArrayList<>();

    /**
     * 添加认证策略
     */
    public AnyMatchAuthenticationStrategy addStrategy(AuthenticationStrategy strategy) {
        strategies.add(strategy);
        return this;
    }

    @Override
    public boolean supports(CredentialRequest request) {
        return strategies.stream().anyMatch(strategy -> strategy.supports(request));
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        AuthenticationException lastException = null;
        for (AuthenticationStrategy strategy : strategies) {
            if (strategy.supports(request)) {
                try {
                    UserPrincipal principal = strategy.authenticate(request);
                    if (principal != null) {
                        return principal;
                    }
                } catch (AuthenticationException e) {
                    lastException = e;
                }
            }
        }
        throw lastException != null ? lastException : new AuthenticationException("No suitable authentication strategy found");
    }
} 