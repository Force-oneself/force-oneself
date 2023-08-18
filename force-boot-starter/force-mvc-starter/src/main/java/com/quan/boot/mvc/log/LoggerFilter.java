package com.quan.boot.mvc.log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
public class LoggerFilter implements Filter {

    private final LoggerProperties properties;

    public LoggerFilter(LoggerProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest) || !(resp instanceof HttpServletResponse)) {
            chain.doFilter(req, resp);
            return;
        }
        if (!properties.isEnabled()) {
            chain.doFilter(req, resp);
            return;
        }
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (properties.isStep()) {
            ServletLoggerRecorder.stepRecords(properties, request, response, () -> chain.doFilter(req, resp));
        } else {
            ServletLoggerRecorder.mergeRecords(properties, request, response, () -> chain.doFilter(req, resp));
        }
    }
}
