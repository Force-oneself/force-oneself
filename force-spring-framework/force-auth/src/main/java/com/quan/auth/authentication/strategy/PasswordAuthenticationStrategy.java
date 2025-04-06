package com.quan.auth.authentication.strategy;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.authentication.request.PasswordCredentialRequest;

/**
 * 密码认证策略实现
 */
public class PasswordAuthenticationStrategy implements AuthenticationStrategy {
    
    private static final String GRANT_TYPE = "password";
    
    @Override
    public boolean supports(CredentialRequest request) {
        return GRANT_TYPE.equalsIgnoreCase(request.getAuthenticationType()) &&
                request instanceof PasswordCredentialRequest;
    }

    @Override
    public UserPrincipal authenticate(CredentialRequest request) throws AuthenticationException {
        if (!(request instanceof PasswordCredentialRequest)) {
            throw new AuthenticationException("Invalid password authentication request");
        }
        
        // 执行密码认证逻辑
        return null;
    }
} 