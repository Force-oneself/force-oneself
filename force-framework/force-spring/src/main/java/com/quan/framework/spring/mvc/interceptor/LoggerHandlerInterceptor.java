package com.quan.framework.spring.mvc.interceptor;

import com.quan.framework.spring.mvc.wrapper.RepeatableHttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.stream.Collectors;

/***
 * LoggerHandlerInterceptor.java
 *
 * @author Force-oneself
 * @date 2023-03-01
 */
// @Component
public class LoggerHandlerInterceptor implements HandlerInterceptor {

    public final static Logger log = LoggerFactory.getLogger(LoggerHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (DispatcherType.ERROR.equals(request.getDispatcherType())) {
            return true;
        }
        final StringBuilder logRecord = new StringBuilder(256)
                .append("\n<================  Request Start  ================>\n")
                .append(request.getMethod())
                .append(" ")
                .append(request.getRequestURL())
                .append(StringUtils.hasLength(request.getQueryString())
                        ? request.getQueryString()
                        : "")
                .append(" ")
                .append(request.getProtocol());
        String header = Collections.list(request.getHeaderNames())
                .stream()
                .map(name -> name + ": " + request.getHeader(name))
                .collect(Collectors.joining("\n", "\n", ""));
        if (StringUtils.hasText(header)) {
            logRecord.append(header);
        }
        logRecord.append("\n\n")
                .append(this.readerBody(request))
                .append("\n<=================  Request End  =================>\n");
        log.info(logRecord.toString());
        return true;
    }

    private String readerBody(HttpServletRequest request) {
        StringBuilder body = new StringBuilder();
        String inputLine;
        try (BufferedReader reader = request.getReader()) {
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
        } catch (Exception e) {
            log.error("读取请求body失败", e);
        }
        return body.toString();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (DispatcherType.ERROR.equals(request.getDispatcherType())) {
            return ;
        }
        postRecord(request, response, null);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (DispatcherType.ERROR.equals(request.getDispatcherType())) {
            return ;
        }
        postRecord(request, response, ex);
    }

    private static void postRecord(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        final StringBuilder logRecord = new StringBuilder(256)
                .append("\n<================  Response Start  ================>\n")
                .append(request.getProtocol())
                .append(" ")
                .append(ex != null ? 500 : response.getStatus());
        String header = response.getHeaderNames()
                .stream()
                .map(name -> name + ": " + response.getHeader(name))
                .collect(Collectors.joining("\n", "\n", ""));
        if (StringUtils.hasText(header)) {
            logRecord.append(header);
        }
        if (ex == null) {
            logRecord.append("\n\n")
                    .append(response instanceof RepeatableHttpServletResponseWrapper
                            ? ((RepeatableHttpServletResponseWrapper) response).byteOutputStream()
                            : "");
        } else {
            logRecord.append("\n\n")
                    .append("发生异常: ")
                    .append(ex.getMessage());
        }
        logRecord.append("\n<=================  Response End  =================>\n");
        log.info(logRecord.toString());
    }
}
