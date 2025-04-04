package com.quan.auth.token;

import com.quan.auth.UserPrincipal;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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
     * 令牌类型（如 Bearer）
     */
    private String tokenType = "Bearer";

    /**
     * 访问令牌过期时间（秒）
     */
    private long expiresIn;

    /**
     * 刷新令牌过期时间（秒）
     */
    private long refreshExpiresIn;

    /**
     * 令牌签发时间
     */
    private long issuedAt;

    /**
     * 令牌主体（用户ID）
     */
    private String subject;

    /**
     * 令牌签发者
     */
    private String issuer;

    /**
     * 令牌接收者
     */
    private String audience;

    /**
     * 用户信息
     */
    private UserPrincipal userPrincipal;

    /**
     * 额外声明信息
     */
    private Map<String, Object> claims = new HashMap<>();

    /**
     * 添加声明信息
     */
    public void addClaim(String name, Object value) {
        claims.put(name, value);
    }

    /**
     * 获取声明信息
     */
    public Object getClaim(String name) {
        return claims.get(name);
    }
}
