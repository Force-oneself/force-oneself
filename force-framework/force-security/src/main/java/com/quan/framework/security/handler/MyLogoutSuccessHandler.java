package com.quan.framework.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Force-oneself
 * @Description MyLogoutSuccessHandler.java
 * @date 2021-07-25
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private final HttpStatus httpStatusToReturn;

    public MyLogoutSuccessHandler(HttpStatus httpStatusToReturn) {
        Assert.notNull(httpStatusToReturn, "状态码不能为空");
        this.httpStatusToReturn = httpStatusToReturn;
    }

    public MyLogoutSuccessHandler() {
        httpStatusToReturn = HttpStatus.OK;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(this.httpStatusToReturn.value());
        response.getWriter().flush();
    }
}
