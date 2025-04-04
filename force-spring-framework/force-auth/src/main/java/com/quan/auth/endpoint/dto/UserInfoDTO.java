package com.quan.auth.endpoint.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 用户信息传输对象
 */
@Getter
@Setter
public class UserInfoDTO {
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
     * 角色列表
     */
    private Set<String> roles;
    
    /**
     * 权限列表
     */
    private Set<String> permissions;
} 