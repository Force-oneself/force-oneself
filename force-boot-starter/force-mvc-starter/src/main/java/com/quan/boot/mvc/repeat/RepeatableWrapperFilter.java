package com.quan.boot.mvc.repeat;


import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * RepeatableWrapperFilter.java
 *
 * @author Force-oneself
 * @date 2023-03-01
 */
public class RepeatableWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 兼容Spring MVC 自带可重复读取的请求和响应
        if (request instanceof ContentCachingRequestWrapper || response instanceof ContentCachingResponseWrapper) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request instanceof HttpServletRequest
                        ? new RepeatableRequestWrapper((HttpServletRequest) request)
                        : request,
                response instanceof HttpServletResponse
                        ? new RepeatableResponseWrapper((HttpServletResponse) response)
                        : response);
    }
}
