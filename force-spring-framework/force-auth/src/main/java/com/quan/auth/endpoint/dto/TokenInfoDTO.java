package com.quan.auth.endpoint.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 令牌验证信息传输对象
 */
@Getter
@Setter
public class TokenInfoDTO {
    
    /**
     * 令牌主体（用户ID）
     */
    private String subject;
    
    /**
     * 是否有效
     */
    private boolean active;
    
    /**
     * 过期时间（秒）
     */
    private long expiresIn;
} 