package com.quan.auth.endpoint;

import com.quan.auth.UserPrincipal;
import com.quan.auth.authentication.AuthenticationException;
import com.quan.auth.authentication.AuthenticationService;
import com.quan.auth.authentication.request.CredentialRequest;
import com.quan.auth.endpoint.dto.TokenDTO;
import com.quan.auth.endpoint.dto.TokenInfoDTO;
import com.quan.auth.endpoint.dto.UserInfoDTO;
import com.quan.auth.token.Token;
import com.quan.auth.token.TokenService;
import lombok.RequiredArgsConstructor;

/**
 * 认证端点，提供认证相关的API接口
 */
@RequiredArgsConstructor
public class AuthEndpoint {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    /**
     * 认证接口
     */
    public R<TokenDTO> authenticate(CredentialRequest request) {
        try {
            // 执行认证
            UserPrincipal principal = authenticationService.authenticate(request);

            // 生成令牌
            Token token = tokenService.createAccessToken(principal);
            return R.success(convertToTokenDTO(token));
        } catch (AuthenticationException | com.quan.auth.token.TokenException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 刷新令牌
     */
    public R<TokenDTO> refreshToken(String refreshToken) {
        try {
            Token token = tokenService.refreshAccessToken(refreshToken);
            return R.success(convertToTokenDTO(token));
        } catch (com.quan.auth.token.TokenException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 验证令牌
     */
    public R<TokenInfoDTO> verifyToken(String accessToken) {
        try {
            Token token = tokenService.verifyToken(accessToken);
            return R.success(convertToTokenInfoDTO(token));
        } catch (com.quan.auth.token.TokenException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 注销令牌
     */
    public R<Void> revokeToken(String token) {
        try {
            if (tokenService.isRevoked(token)) {
                return R.error("Token already revoked");
            }
            tokenService.revokeToken(token);
            return R.success(null);
        } catch (com.quan.auth.token.TokenException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 获取当前认证用户信息
     */
    public R<UserInfoDTO> getCurrentUser() {
        try {
            UserPrincipal principal = authenticationService.getCurrentUser();
            return R.success(convertToUserInfoDTO(principal));
        } catch (AuthenticationException e) {
            return R.error(e.getMessage());
        }
    }

    /**
     * 登出
     */
    public R<Void> logout() {
        authenticationService.logout();
        return R.success(null);
    }

    /**
     * 转换为令牌DTO
     */
    private TokenDTO convertToTokenDTO(Token token) {
        TokenDTO dto = new TokenDTO();
        dto.setAccessToken(token.getAccessToken());
        dto.setRefreshToken(token.getRefreshToken());
        dto.setTokenType(token.getTokenType());
        dto.setExpiresIn(token.getExpiresIn());
        return dto;
    }

    /**
     * 转换为令牌信息DTO
     */
    private TokenInfoDTO convertToTokenInfoDTO(Token token) {
        TokenInfoDTO dto = new TokenInfoDTO();
        dto.setSubject(token.getSubject());
        dto.setActive(!tokenService.isExpired(token.getAccessToken()));
        dto.setExpiresIn(token.getExpiresIn());
        return dto;
    }

    /**
     * 转换为用户信息DTO
     */
    private UserInfoDTO convertToUserInfoDTO(UserPrincipal principal) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setUserId(principal.getUserId());
        dto.setUsername(principal.getUsername());
        dto.setNickname(principal.getUsername());
        return dto;
    }
} 