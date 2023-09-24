package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.limit.local.CounterRateLimiter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
public class RateLimitFilter implements Filter {

    private final RateLimitProperties properties;

    private final Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>(32);

    public RateLimitFilter(RateLimitProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        if (!properties.getPaths().containsKey(servletPath)) {
            chain.doFilter(request, response);
            return;
        }
        RateLimitPath rateLimitPath = properties.getPaths().get(servletPath);
        RateLimiter rateLimiter = rateLimiters.putIfAbsent(rateLimitPath.getKey(), new CounterRateLimiter(rateLimitPath.getTime(), rateLimitPath.getFlow()));
        if (rateLimiter != null && !rateLimiter.rateLimit()) {
            throw new RuntimeException("请求被限流");
        }
        chain.doFilter(request, response);
    }
}
