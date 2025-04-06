package com.quan.auth.authentication;

import com.quan.auth.authentication.strategy.AuthenticationStrategy;
import com.quan.auth.authentication.request.CredentialRequest;

import java.util.List;

/**
 * 认证策略排序器接口
 */
public interface StrategyOrdered {

    /**
     * 对认证策略进行排序
     *
     * @param strategies 待排序的策略列表
     * @param request 认证请求
     * @return 排序后的策略列表
     */
    List<AuthenticationStrategy> order(List<AuthenticationStrategy> strategies, CredentialRequest request);
} 