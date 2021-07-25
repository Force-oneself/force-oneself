package com.quan.framework.security.config;

import com.quan.framework.security.encoder.MyPasswordEncoder;
import com.quan.framework.security.handler.MyAuthenticationFailureHandler;
import com.quan.framework.security.handler.MyAuthenticationSuccessHandler;
import com.quan.framework.security.handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-04-12
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors()
                .and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录URL
                .antMatchers("/login").permitAll()
                .antMatchers("/hello").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated()
                // 退出登录处理器
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/success/url")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                // 登录处理
                .and().formLogin()
//                .loginPage("/login")
//                .failureForwardUrl("/failure")
                .failureHandler(new MyAuthenticationFailureHandler())
//                .successForwardUrl("/success/page")
                .successHandler(new MyAuthenticationSuccessHandler())
//                .passwordParameter("password")
//                .usernameParameter("username")
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }
}
