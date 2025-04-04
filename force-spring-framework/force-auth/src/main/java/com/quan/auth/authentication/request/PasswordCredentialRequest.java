package com.quan.auth.authentication.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 密码认证请求
 */
@Getter
@Setter
public class PasswordCredentialRequest extends BaseCredentialRequest {
    
    /**
     * 密码
     */
    private String password;
    
    @Override
    public String getCredential() {
        return password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        setCredential(password);
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return getCredential();
    }
} 