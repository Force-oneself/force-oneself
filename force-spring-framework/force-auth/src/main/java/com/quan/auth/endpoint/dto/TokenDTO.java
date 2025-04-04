package com.quan.auth.endpoint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 令牌信息传输对象
 */
@Getter
@Setter
public class TokenDTO {
    
    /**
     * 访问令牌
     */
    private String accessToken;
    
    /**
     * 刷新令牌
     */
    private String refreshToken;
    
    /**
     * 令牌类型
     */
    private String tokenType;
    
    /**
     * 过期时间（秒）
     */
    private long expiresIn;
} 