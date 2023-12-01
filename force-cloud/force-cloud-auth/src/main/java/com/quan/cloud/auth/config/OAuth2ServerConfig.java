package com.quan.cloud.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Oauth2 认证服务配置
 *
 * @author Force-oneself
 * @date 2023-11-21
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * 自定义身份认证
     */
    private final UserDetailsService userDetailsService;

    public OAuth2ServerConfig(AuthenticationManager authenticationManager, DataSource dataSource, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 存储client信息
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore() {
        // token存储
        return new JdbcTokenStore(dataSource);
//        return new InMemoryTokenStore();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        // 授权码模式
        return new JdbcAuthorizationCodeServices(dataSource);
//        return new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置 client信息从数据库中取
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // token存储方式
                .tokenStore(tokenStore())
                // 开启密码验证，由 WebSecurityConfigurerAdapter
                .authenticationManager(authenticationManager)
                // 读取验证用户信息
                .userDetailsService(userDetailsService)
                // 自定义授权跳转
                .pathMapping("/oauth/confirm_access", "/custom/confirm_access")
                .authorizationCodeServices(authorizationCodeServices())
                .setClientDetailsService(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //  配置Endpoint,允许请求
        security
                // 开启/oauth/token_key 验证端口-无权限
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token 验证端口-需权限
                .checkTokenAccess("isAuthenticated()")
                // 允许表单认证
                .allowFormAuthenticationForClients()

                // 配置BCrypt加密
                .passwordEncoder(passwordEncoder());
    }
}
