package com.quan.auth.authentication.request;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础认证请求实现
 */
@Getter
@Setter
public class BaseCredentialRequest implements CredentialRequest {

    /**
     * 认证类型
     */
    private String authenticationType;
    
    /**
     * 认证主体标识
     */
    private String principal;
    
    /**
     * 认证凭证
     */
    private String credential;
    
    /**
     * 认证来源
     */
    private String source = "unknown";
    
    /**
     * 设备信息
     */
    private Map<String, String> deviceInfo = new HashMap<>();
    
    /**
     * 扩展参数
     */
    private Map<String, Object> extendParams = new HashMap<>();
    
    /**
     * 是否记住登录
     */
    private boolean rememberMe;
    
    /**
     * 认证请求时间戳
     */
    private long timestamp = System.currentTimeMillis();

    @Override
    public String getAuthenticationType() {
        return authenticationType;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public String getCredential() {
        return credential;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public Map<String, String> getDeviceInfo() {
        return deviceInfo;
    }

    @Override
    public Map<String, Object> getExtendParams() {
        return extendParams;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 