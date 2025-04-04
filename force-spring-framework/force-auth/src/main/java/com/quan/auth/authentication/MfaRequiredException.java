package com.quan.auth.authentication;

import lombok.Getter;

/**
 * 多因素认证需要继续的异常
 */
@Getter
public class MfaRequiredException extends AuthenticationException {

    /**
     * 当前步骤
     */
    private final int currentStep;

    /**
     * 总步骤数
     */
    private final int totalSteps;

    public MfaRequiredException(String message, int currentStep, int totalSteps) {
        super(message);
        this.currentStep = currentStep;
        this.totalSteps = totalSteps;
    }
} 