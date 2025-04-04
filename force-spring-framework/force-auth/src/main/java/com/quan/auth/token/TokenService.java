package com.quan.auth.token;

import com.quan.auth.UserPrincipal;

/**
 * TokenService 接口定义了与令牌操作相关的方法，
 * 这些方法用于创建、刷新、验证和撤销令牌。
 * 该接口提供了一套统一的操作规范，具体的实现类可以根据不同的业务需求和安全策略来实现这些方法。
 *
 * @author Force-oneself
 * @date 2025-04-03
 */
public interface TokenService {

    /**
     * 创建访问令牌
     * 根据用户主体信息创建一个新的访问令牌。
     * 该方法会使用用户的相关信息生成一个有效的令牌，用于后续的资源访问。
     *
     * @param user 用户主体信息，包含用户的基本信息和权限信息等
     * @return 令牌信息，包含访问令牌、刷新令牌、过期时间等
     */
    Token createAccessToken(UserPrincipal user);

    /**
     * 刷新访问令牌
     * 当访问令牌过期时，可以使用刷新令牌来获取一个新的访问令牌。
     * 该方法会验证刷新令牌的有效性，并生成一个新的访问令牌。
     *
     * @param refreshToken 刷新令牌，用于验证并获取新的访问令牌
     * @return 新令牌信息，包含新的访问令牌、刷新令牌、过期时间等
     * @throws TokenException 令牌异常，当刷新令牌无效或过期时抛出该异常
     */
    Token refreshAccessToken(String refreshToken) throws TokenException;

    /**
     * 验证令牌有效性
     * 验证给定的访问令牌是否有效，并解析出其中的令牌声明。
     * 该方法会检查令牌的签名、过期时间等信息，确保令牌的合法性。
     *
     * @param accessToken 访问令牌，用于验证和解析
     * @return 解析后的令牌声明，包含用户的相关信息和权限信息等
     * @throws TokenException 验证失败异常，当令牌无效或过期时抛出该异常
     */
    TokenClaims verifyToken(String accessToken) throws TokenException;

    /**
     * 主动撤销令牌
     * 当需要提前终止某个访问令牌的有效性时，可以调用该方法。
     * 该方法会将指定的访问令牌标记为无效，使其无法再用于资源访问。
     *
     * @param accessToken 要撤销的令牌，需要撤销的访问令牌
     */
    void revokeToken(String accessToken);
}
