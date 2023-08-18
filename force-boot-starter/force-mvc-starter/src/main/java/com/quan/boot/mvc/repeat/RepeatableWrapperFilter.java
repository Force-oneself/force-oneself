package com.quan.boot.mvc.repeat;


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
        chain.doFilter(request instanceof HttpServletRequest
                        ? new RepeatableRequestWrapper((HttpServletRequest) request)
                        : request,
                response instanceof HttpServletResponse
                        ? new RepeatableResponseWrapper((HttpServletResponse) response)
                        : response);
    }
}
