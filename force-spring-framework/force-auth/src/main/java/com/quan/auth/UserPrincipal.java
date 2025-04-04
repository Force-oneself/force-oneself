package com.quan.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * 该类用于表示用户的主要信息，包含用户的基本信息、权限信息以及额外信息。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
@Getter
@Setter
public class UserPrincipal {

    /**
     * 用户的唯一标识符
     */
    private String userId;

    /**
     * 用户的用户名
     */
    private String username;

    /**
     * 用户是否启用的标志
     */
    private boolean enabled;

    /**
     * 用户拥有的权限集合
     */
    private Set<String> authorities;

    /**
     * 用户的额外信息，以键值对的形式存储
     */
    private Map<String, Object> additionalInfo;

    /**
     * 检查用户是否拥有指定的权限。
     *
     * @param authority 要检查的权限字符串
     * @return 如果用户拥有该权限，则返回 true；否则返回 false
     */
    public boolean hasAuthority(String authority) {
        return authorities.contains(authority);
    }
}
