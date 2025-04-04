package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.*;
import com.quan.auth.authentication.request.CredentialRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 多因素认证策略，按顺序执行多个认证步骤
 */
public class MfaAuthenticationStrategy implements AuthenticationStrategy {

    /**
     * 认证步骤列表
     */
    private final List<AuthenticationStrategy> steps = new ArrayList<>();

    /**
     * 添加认证步骤
     */
    public MfaAuthenticationStrategy addStep(AuthenticationStrategy strategy) {
        steps.add(strategy);
        return this;
    }

    @Override
    public boolean supports(CredentialRequest request) {
        MfaContext context = getMfaContext(request);
        if (context.getCurrentStep() >= steps.size()) {
            return false;
        }
        return steps.get(context.getCurrentStep()).supports(request);
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        MfaContext context = getMfaContext(request);
        if (context.getCurrentStep() >= steps.size()) {
            throw new AuthenticationException("All authentication steps completed");
        }

        AuthenticationStrategy currentStrategy = steps.get(context.getCurrentStep());
        if (!currentStrategy.supports(request)) {
            throw new AuthenticationException("Current step does not support this request");
        }

        UserPrincipal principal = currentStrategy.authenticate(request);
        if (principal == null) {
            throw new AuthenticationException("Authentication failed at step " + (context.getCurrentStep() + 1));
        }

        // 保存认证结果
        context.setPrincipal(principal);
        context.nextStep();
        
        // 如果所有步骤都完成，返回最终认证结果
        if (context.getCurrentStep() == steps.size()) {
            UserPrincipal finalPrincipal = context.getPrincipal();
            context.reset();
            return finalPrincipal;
        }
        
        // 否则抛出需要继续认证的异常
        throw new MfaRequiredException("Additional authentication required", context.getCurrentStep(), steps.size());
    }

    /**
     * 获取认证上下文
     */
    private MfaContext getMfaContext(CredentialRequest request) {
        MfaContext context = request.getAttribute(MfaContext.class);
        if (context == null) {
            context = MfaContext.create();
            request.setAttribute(MfaContext.class, context);
        }
        return context;
    }
} 