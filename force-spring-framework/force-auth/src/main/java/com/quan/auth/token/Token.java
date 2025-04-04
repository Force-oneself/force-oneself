package com.quan.auth.token;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 该类用于表示认证过程中使用的令牌信息，包含访问令牌、刷新令牌、过期时间和令牌类型等。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
@Getter
@Setter
public class Token {

    /**
     * 访问令牌，用于访问受保护的资源
     */
    private String accessToken;

    /**
     * 刷新令牌，用于在访问令牌过期时获取新的访问令牌
     */
    private String refreshToken;

    /**
     * 访问令牌的过期时间
     */
    private Instant expiresAt;

    /**
     * 令牌的类型，例如 "Bearer"
     */
    private String tokenType;

}
