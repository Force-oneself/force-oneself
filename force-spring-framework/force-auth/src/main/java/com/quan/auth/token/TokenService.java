package com.quan.auth.token;

import com.quan.auth.UserPrincipal;

/**
 * 令牌服务接口，提供令牌的创建、刷新、验证和撤销等功能
 */
public interface TokenService {

    /**
     * 创建访问令牌
     *
     * @param principal 用户主体信息
     * @return 令牌信息
     * @throws TokenException 令牌创建异常
     */
    Token createAccessToken(UserPrincipal principal) throws TokenException;

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 新的令牌信息
     * @throws TokenException 令牌刷新异常
     */
    Token refreshAccessToken(String refreshToken) throws TokenException;

    /**
     * 验证令牌
     *
     * @param accessToken 访问令牌
     * @return 令牌信息
     * @throws TokenException 令牌验证异常
     */
    Token verifyToken(String accessToken) throws TokenException;

    /**
     * 撤销令牌
     *
     * @param token 要撤销的令牌
     * @throws TokenException 令牌撤销异常
     */
    void revokeToken(String token) throws TokenException;

    /**
     * 检查令牌是否过期
     *
     * @param token 要检查的令牌
     * @return true表示已过期，false表示未过期
     */
    boolean isExpired(String token);

    /**
     * 检查令牌是否已被撤销
     *
     * @param token 要检查的令牌
     * @return true表示已被撤销，false表示未被撤销
     */
    boolean isRevoked(String token);
}
