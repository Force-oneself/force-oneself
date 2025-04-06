package com.quan.auth.endpoint;

import com.quan.auth.authentication.AuthenticationService;
import com.quan.auth.authorization.AuthorizationService;
import com.quan.auth.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-04-06
 */
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/sso")
public  class SSOEndpoint {

    private final AuthorizationService authorizationService;
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    private final AuthorizationEndpoint baseEndpoint;
    private final TokenEndpoint tokenEndpoint;

    @RequestMapping("/authorize")
    public ModelAndView authorize(Map<String, Object> model,
                                  @RequestParam Map<String, String> parameters,
                                  SessionStatus sessionStatus,
                                  Principal principal) {

        // 自定义授权前逻辑
        log.info("Authorization request with params: {}", parameters);

        // TODO 使用AuthorizationService 自定义授权后逻辑
        return baseEndpoint.authorize(model, parameters, sessionStatus, principal);
    }


    @PostMapping(value = "/token")
    public ResponseEntity<OAuth2AccessToken> token(Principal principal,
                                                   @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {

        // 前置处理
        log.info("Token request received with parameters: {}", parameters);

        // TODO 使用TokenService适配
        ResponseEntity<OAuth2AccessToken> response = tokenEndpoint.postAccessToken(principal, parameters);

        // 后置处理
        OAuth2AccessToken token = response.getBody();
        assert token != null;
        log.info("Token generated: {}", token.getValue());

        // 自定义响应格式
        return ResponseEntity.ok()
                .header("Custom-Header", "value")
                .body(token);
    }

}
