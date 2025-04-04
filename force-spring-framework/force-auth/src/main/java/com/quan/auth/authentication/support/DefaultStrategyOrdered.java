package com.quan.auth.authentication.support;

import com.quan.auth.authentication.AuthenticationStrategy;
import com.quan.auth.authentication.request.CredentialRequest;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 认证策略排序器
 */
@Component
public class DefaultStrategyOrdered implements StrategyOrdered {

    /**
     * 对认证策略进行排序
     *
     * @param strategies 待排序的策略列表
     * @param request 认证请求
     * @return 排序后的策略列表
     */
    public List<AuthenticationStrategy> order(List<AuthenticationStrategy> strategies, CredentialRequest request) {
        AnnotationAwareOrderComparator.sort(strategies);
        return strategies;
    }
} 