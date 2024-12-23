package com.quan.boot.mvc.log;

import com.quan.boot.mvc.repeat.ResponseRepeatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 打印请求日志过滤器
 *
 * @author Force-oneself
 * @date 2023-03-01
 */
public class LoggerFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(LoggerFilter.class);

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
        // 日志级别
        int logLevel = properties.getLevel();
        StringBuilder logFormat = new StringBuilder()
                .append("\n==================== Request ====================\n")
                .append(preRecord(logLevel, request));
        // .append("\n==================== Response ===================\n");

        long start = System.currentTimeMillis();
        try {
            chain.doFilter(request, response);
            logFormat.append(postRecord(logLevel, response, null));
        } catch (Exception e) {
            logFormat.append(postRecord(logLevel, response, e));
            throw e;
        } finally {
            log.info(logFormat.append("\nExecution cost: ")
                    .append(System.currentTimeMillis() - start)
                    .append(" ms")
                    .append("\n=================================================\n")
                    .toString());
        }
    }

    /**
     * 响应后日志记录
     *
     * @param level    日志级别
     * @param response 响应
     * @param ex       异常
     * @return 日志记录
     */
    private String postRecord(int level, HttpServletResponse response, Exception ex) {
        final StringBuilder logRecord = new StringBuilder(256);
        // basic
        if (LoggerLevel.RESPONSE_BASIC.isEnable(level)) {
            logRecord.append("\n")
                    .append("Status Code: ")
                    .append(ex != null ? 500 : response.getStatus())
                    .append(" ")
                    .append(ex != null ? "Fail" : "OK");
        }
        // header
        if (LoggerLevel.RESPONSE_HEADER.isEnable(level)) {
            String header = response.getHeaderNames()
                    .stream()
                    .map(name -> name + ": " + response.getHeader(name))
                    .collect(Collectors.joining("\n", "\n", ""));
            if (StringUtils.hasText(header)) {
                logRecord.append(header);
            }
        }
        // body
        if (ex == null && LoggerLevel.RESPONSE_BODY.isEnable(level)) {
            if (response instanceof ResponseRepeatable) {
                logRecord.append("\n\n")
                        .append("Response Body: ")
                        .append(String.copyValueOf(
                                new String(((ResponseRepeatable) response).responseBody()).toCharArray()));
            }
        }
        // error
        if (ex != null && LoggerLevel.RESPONSE_ERROR.isEnable(level)) {
            logRecord.append("\n\n")
                    .append("Error: ")
                    .append(ex.getMessage());
        }
        return logRecord.toString();
    }

    /**
     * 请求前日志记录
     *
     * @param level   日志级别
     * @param request 请求
     * @return 日志记录
     */
    private String preRecord(int level, HttpServletRequest request) {
        final StringBuilder logRecord = new StringBuilder(256);
        // basic
        final String requestMethod = request.getMethod();
        if (LoggerLevel.REQUEST_BASIC.isEnable(level)) {
            logRecord.append(requestMethod)
                    .append(" ")
                    .append(request.getRequestURL())
                    .append(StringUtils.hasLength(request.getQueryString())
                            ? "?" + request.getQueryString()
                            : "")
                    .append(" ")
                    .append(request.getProtocol());
        }
        // header
        if (LoggerLevel.REQUEST_HEADER.isEnable(level)) {
            String header = Collections.list(request.getHeaderNames())
                    .stream()
                    .map(name -> name + ": " + request.getHeader(name))
                    .collect(Collectors.joining("\n", "\n", ""));
            if (StringUtils.hasText(header)) {
                logRecord.append(header);
            }
        }
        // body
        if (LoggerLevel.REQUEST_BODY.isEnable(level)) {
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                logRecord.append("\n\n")
                        .append("Request Payload: ")
                        .append(readerBody(request));
            }
        }
        return logRecord.toString();
    }

    /**
     * 读取请求body
     *
     * @param request 请求
     * @return 请求body
     */
    private String readerBody(HttpServletRequest request) {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
        } catch (Exception e) {
            log.error("读取请求body失败", e);
        }
        return body.toString();
    }
}
