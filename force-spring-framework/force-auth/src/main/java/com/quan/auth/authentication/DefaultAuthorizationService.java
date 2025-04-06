package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.authentication.strategy.AuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 默认认证服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAuthorizationService implements AuthenticationService {

    /**
     * 认证策略排序器
     */
    private final StrategyOrdered strategyOrdered;

    /**
     * 认证策略列表
     */
    private final List<AuthenticationStrategy> strategies;

    /**
     * 认证监听器列表
     */
    private final List<AuthenticationListener> listeners;

    @Override
    public UserPrincipal authenticate(CredentialRequest request) {
        // 获取排序后的策略列表
        List<AuthenticationStrategy> sortedStrategies = strategyOrdered.order(strategies, request);

        if (sortedStrategies.isEmpty()) {
            throw new AuthenticationException("No suitable authentication strategy found");
        }

        try {
            for (AuthenticationStrategy strategy : sortedStrategies) {
                if (strategy.supports(request)) {
                    UserPrincipal authorize = strategy.authenticate(request);
                    listeners.forEach(listener -> listener.onAuthenticationSuccess(request, authorize));
                    return authorize;
                }
            }
        } catch (Exception e) {
            // 通知认证失败
            for (AuthenticationListener listener : listeners) {
                listener.onAuthenticationFailure(request, e);
            }
            throw e;
        }
        throw new AuthenticationException("Authentication failed");
    }

    @Override
    public void logout() {

    }

    @Override
    public UserPrincipal getCurrentUser() throws AuthenticationException {
        return null;
    }
} 