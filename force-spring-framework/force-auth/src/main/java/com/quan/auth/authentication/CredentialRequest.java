package com.quan.auth.authentication;

/**
 * CredentialRequest 接口用于定义一个凭证请求的规范。
 * 实现该接口的类可以表示一个具体的凭证请求，例如用户名密码请求、令牌请求等。
 * 通常在认证流程中，会使用实现该接口的对象来封装客户端提供的认证凭证信息。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface CredentialRequest {

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getUsername();

    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPassword();

    /**
     * 获取令牌
     *
     * @return 令牌
     */
    String getToken();

}
