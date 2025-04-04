package com.quan.auth.token;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

/**
 * TokenClaims 类用于封装 JWT（JSON Web Token）中的声明信息。
 * 这些声明包含了与令牌相关的用户信息和权限信息等。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
@Getter
@Setter
public class TokenClaims {

    /**
     * JWT 的主题，通常用于标识令牌所代表的用户或实体。
     */
    private String subject;

    /**
     * 用户拥有的权限集合，用于授权和访问控制。
     */
    private Set<String> authorities;

    /**
     * 令牌的发布时间，记录令牌生成的时刻。
     */
    private Instant issuedAt;

    /**
     * 令牌的过期时间，超过此时间令牌将失效。
     */
    private Instant expiration;

}
