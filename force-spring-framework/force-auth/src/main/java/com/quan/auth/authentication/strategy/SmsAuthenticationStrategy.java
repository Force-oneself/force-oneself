package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.authentication.request.SmsCredentialRequest;

/**
 * 短信认证策略实现
 */
public class SmsAuthenticationStrategy implements AuthenticationStrategy {
    
    private static final String GRANT_TYPE = "sms";
    
    @Override
    public boolean supports(CredentialRequest request) {
        return GRANT_TYPE.equalsIgnoreCase(request.getAuthenticationType()) &&
                request instanceof SmsCredentialRequest;
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        if (!(request instanceof SmsCredentialRequest)) {
            throw new AuthenticationException("Invalid sms authentication request");
        }
        
        // 执行短信认证逻辑
        return null;
    }
} 