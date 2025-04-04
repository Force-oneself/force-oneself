package com.quan.framework.security.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

/**
 * @author Force-oneself
 * @Description SecurityController.java
 * @date 2021-07-26
 */
@RestController
public class SecurityController {

    private final AuthorizationEndpoint authorizationEndpoint;
    private final TokenEndpoint tokenEndpoint;
    private final AuthenticationManager authenticationManager;

    public SecurityController(AuthorizationEndpoint authorizationEndpoint, TokenEndpoint tokenEndpoint1, AuthenticationManager authenticationManager) {
        this.authorizationEndpoint = authorizationEndpoint;
        this.tokenEndpoint = tokenEndpoint1;
        this.authenticationManager = authenticationManager;
    }

    /**
     * <a href="http://localhost:8080/custom/authorize?response_type=code&client_id=client-id&redirect_uri=http://localhost:8080/callback&scope=read">...</a>
     *
     * @param model         模型
     * @param parameters    请求参数
     * @param sessionStatus 会话状态
     * @param principal     用户信息
     * @return 授权页面
     */
    @GetMapping("/custom/authorize")
    public ModelAndView authorize(Map<String, Object> model,
                                  @RequestParam Map<String, String> parameters,
                                  SessionStatus sessionStatus,
                                  Principal principal) {

        // 自定义逻辑
        if (!parameters.containsKey("response_type")) {
            // 返回错误页面
            return new ModelAndView("error", "error", "Missing response_type parameter");
        }

        // 调用默认的授权端点逻辑
        return authorizationEndpoint.authorize(model, parameters, sessionStatus, principal);
    }

    /**
     * curl -X POST http://localhost:8080/custom/token \
     * -H "Authorization: Basic Y2xpZW50LWlkOmNsaWVudC1zZWNyZXQ=" \
     * -d "grant_type=authorization_code" \
     * -d "code=<授权码>" \ 可以使用JWT的方式
     * -d "redirect_uri=http://localhost:8080/callback"
     *
     * @param principal  用户信息
     * @param parameters 请求参数
     * @return 令牌信息
     * @throws HttpRequestMethodNotSupportedException 不支持的请求方法
     */
    @PostMapping("/custom/token")
    public Object getToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        // 自定义逻辑
        if (!parameters.containsKey("grant_type")) {
            throw new IllegalArgumentException("Missing grant_type parameter");
        }
        // 调用默认的令牌端点逻辑
        return tokenEndpoint.postAccessToken(principal, parameters);
    }
}
