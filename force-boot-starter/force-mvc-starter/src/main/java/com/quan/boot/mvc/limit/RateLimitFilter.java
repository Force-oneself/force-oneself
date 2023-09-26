package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.limit.local.CounterRateLimiter;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
public class RateLimitFilter implements Filter {

    private final RateLimitProperties properties;

    private final Map<RateLimitPath, RateLimiter> rateLimiters = new ConcurrentHashMap<>(32);

    public RateLimitFilter(RateLimitProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getRequestURI();
        properties.getPaths()
                .stream()
                .filter(obj -> obj.getPath().equals(servletPath))
                .filter(rateLimitPath -> this.realPath(rateLimitPath, request, response))
                .findFirst()
                .ifPresent(path -> {
                    RateLimiter rateLimiter = rateLimiters.computeIfAbsent(path, p -> new CounterRateLimiter(p.getTime(), p.getFlow()));
                    if (!rateLimiter.rateLimit()) {
                        throw new RuntimeException("请求被限流");
                    }
                });
        chain.doFilter(request, response);
    }

    private boolean realPath(RateLimitPath rateLimitPath, ServletRequest request, ServletResponse response) {
        return true;
    }
}
