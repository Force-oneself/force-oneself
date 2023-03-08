package com.quan.framework.spring.mvc.filter;


import com.quan.framework.spring.mvc.wrapper.RepeatableHttpServletRequestWrapper;
import com.quan.framework.spring.mvc.wrapper.RepeatableHttpServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
        chain.doFilter(request instanceof HttpServletRequest
                        ? new RepeatableHttpServletRequestWrapper((HttpServletRequest) request)
                        : request,
                response instanceof HttpServletResponse
                        ? new RepeatableHttpServletResponseWrapper((HttpServletResponse) response)
                        : response);
    }
}
