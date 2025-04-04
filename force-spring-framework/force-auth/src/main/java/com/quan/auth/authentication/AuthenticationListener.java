package com.quan.auth.authentication;

import com.quan.auth.UserPrincipal;

/**
 * 认证监听器接口，用于监听认证过程中的相关事件。
 * 实现该接口的类可以在认证成功、失败等关键节点执行特定的逻辑，
 * 比如记录日志、更新用户状态等。
 * 此接口为认证流程提供了一种可扩展的机制，方便在不同的认证场景下添加额外的处理逻辑。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface AuthenticationListener {

    /**
     * 当认证成功时触发的方法。
     * 此方法会在用户认证成功后被调用，可用于执行认证成功后的操作，如记录登录日志、更新用户状态等。
     * 通常会在认证服务中调用此方法，将认证请求信息和认证成功后的用户主体信息传递进来。
     *
     * @param request   用户提交的认证请求，包含用户的认证凭证信息，如用户名、密码等
     * @param principal 认证成功后的用户主体信息，包含用户的基本信息和权限信息，如用户 ID、角色等
     */
    void onAuthenticationSuccess(CredentialRequest request, UserPrincipal principal);

    /**
     * 当认证失败时触发的方法。
     * 此方法会在用户认证失败后被调用，可用于执行认证失败后的操作，如记录失败日志、锁定用户账号等。
     * 一般在认证服务验证凭证不通过时调用此方法，传递认证请求信息和认证失败的异常。
     *
     * @param request   用户提交的认证请求，包含用户的认证凭证信息，如用户名、密码等
     * @param exception 认证过程中抛出的异常，包含认证失败的详细原因，如凭证无效、用户不存在等
     */
    void onAuthenticationFailure(CredentialRequest request, AuthenticationException exception);

}
