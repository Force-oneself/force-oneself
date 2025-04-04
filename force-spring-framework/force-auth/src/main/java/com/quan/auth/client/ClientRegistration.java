package com.quan.auth.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 该类用于表示客户端注册信息
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
@Getter
@Setter
public class ClientRegistration {

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 重定向 URI 集合
     */
    private Set<String> redirectUris;

    /**
     * 授权类型集合
     */
    private Set<String> grantTypes;

    /**
     * 权限范围集合
     */
    private Set<String> scopes;
}
