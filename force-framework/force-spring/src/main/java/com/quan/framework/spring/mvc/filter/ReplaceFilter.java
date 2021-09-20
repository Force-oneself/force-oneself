package com.quan.framework.spring.mvc.filter;


import com.quan.framework.spring.mvc.wrapper.BodyStorageHttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Force-oneself
 * @Description ReplaceFilter 将servlet替换为可以支持重复读取body字节流的实现
 * @date 2021-08-24
 */
@Component
public class ReplaceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if(request instanceof HttpServletRequest) {
            requestWrapper = new BodyStorageHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if(requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }
}
