package com.quan.framework.spring.mvc.xss;

import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
public class XssRequestFilter implements Filter {

    private final XssProperties properties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public XssRequestFilter(XssProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(this.realRequest(request), response);
    }

    private ServletRequest realRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            return request;
        }
        if (!properties.isEnable()) {
            return request;
        }
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        boolean skip = properties.getSkipUrls()
                .stream()
                .anyMatch(pattern -> antPathMatcher.match(pattern, servletRequest.getServletPath()));
        return skip ? request : new XssHttpServletRequestWrapper(servletRequest);
    }
}
