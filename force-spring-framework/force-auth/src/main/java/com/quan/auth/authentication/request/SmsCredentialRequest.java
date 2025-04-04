package com.quan.auth.authentication.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 短信认证请求
 */
@Getter
@Setter
public class SmsCredentialRequest extends BaseCredentialRequest {
    
    /**
     * 设置手机号
     */
    public void setMobile(String mobile) {
        setPrincipal(mobile);
    }
    
    /**
     * 获取手机号
     */
    public String getMobile() {
        return getPrincipal();
    }
    
    /**
     * 设置验证码
     */
    public void setSmsCode(String smsCode) {
        setCredential(smsCode);
    }
    
    /**
     * 获取验证码
     */
    public String getSmsCode() {
        return getCredential();
    }
} 