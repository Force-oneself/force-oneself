package com.quan.auth.authorization;

import com.quan.auth.UserPrincipal;

import java.util.Set;

/**
 * 资源访问授权服务
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthorizationService {

    /**
     * 检查权限
     *
     * @param user     用户主体
     * @param resource 要访问的资源标识
     * @param action   请求的操作类型
     * @throws AccessDeniedException 无权限异常
     */
    void checkPermission(UserPrincipal user, String resource, String action) throws AccessDeniedException;

    /**
     * 检查角色
     *
     * @param user 用户主体
     * @param role 要求拥有的角色
     * @throws AccessDeniedException 角色缺失异常
     */
    void checkRole(UserPrincipal user, String role) throws AccessDeniedException;

    /**
     * 批量获取权限列表
     *
     * @param user 用户主体
     * @return 权限集合
     */
    Set<String> getPermissions(UserPrincipal user);

}
