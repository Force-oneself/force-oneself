package com.quan.boot.mvc.limit;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
public class RateLimitFilter implements Filter {

    private final RateLimitManager manager;

    public RateLimitFilter(RateLimitManager manager) {
        this.manager = manager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getRequestURI();
        if (!manager.limit(servletPath)) {
            throw new RuntimeException("请求被限流");
        }
        chain.doFilter(request, response);
    }

}
