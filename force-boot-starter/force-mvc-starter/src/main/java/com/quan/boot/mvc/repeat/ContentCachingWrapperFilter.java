package com.quan.boot.mvc.repeat;


import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * ContentCachingWrapperFilter.java
 *
 * @author Force-oneself
 * @date 2023-03-01
 */
public class ContentCachingWrapperFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request instanceof HttpServletRequest
                        ? new ContentCachingRequestWrapper((HttpServletRequest) request)
                        : request,
                response instanceof HttpServletResponse
                        ? new ContentCachingResponseWrapper((HttpServletResponse) response)
                        : response);
    }
}
