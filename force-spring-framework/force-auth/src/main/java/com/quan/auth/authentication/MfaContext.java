package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;
import lombok.Getter;
import lombok.Setter;

/**
 * 多因素认证上下文，用于存储认证状态
 */
@Getter
@Setter
public class MfaContext {

    /**
     * 当前认证步骤索引
     */
    private int currentStep = 0;

    /**
     * 认证主体
     */
    private UserPrincipal principal;

    /**
     * 创建新的认证上下文
     */
    public static MfaContext create() {
        return new MfaContext();
    }

    /**
     * 移动到下一步
     */
    public void nextStep() {
        currentStep++;
    }

    /**
     * 重置状态
     */
    public void reset() {
        currentStep = 0;
        principal = null;
    }
} 