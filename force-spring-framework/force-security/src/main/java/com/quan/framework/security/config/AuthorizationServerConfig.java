package com.quan.framework.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-03-19
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-id") // 客户端 ID
                .secret("client-secret") // 客户端密钥
                .authorizedGrantTypes("authorization_code", "password", "refresh_token") // 授权类型
                .scopes("read", "write") // 权限范围
                .redirectUris("http://localhost:8080/callback") // 回调地址
                .accessTokenValiditySeconds(3600) // 访问令牌有效期
                .refreshTokenValiditySeconds(86400); // 刷新令牌有效期
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .pathMapping("/oauth/token", "/custom/token") // 自定义令牌端点
                .pathMapping("/oauth/authorize", "/custom/authorize") // 自定义授权端点
                .pathMapping("/oauth/check_token", "/custom/check_token"); // 自定义令牌检查端点
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()") // 公开 /oauth/token_key
                .checkTokenAccess("isAuthenticated()"); // 验证 /oauth/check_token
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("password")
                        .roles("USER")
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // 仅用于测试，生产环境使用 BCryptPasswordEncoder
    }
}
