package com.quan.auth.authorization;

import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.token.Token;

/**
 * 资源访问授权服务
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthorizationService {

    /**
     * 认证
     *
     * @param request 认证请求
     * @return 认证用户信息
     */
    Token authenticate(CredentialRequest request);

}
