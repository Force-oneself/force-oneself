package com.quan.boot.mvc.log;

import com.quan.boot.mvc.repeat.ResponseRepeatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
public final class ServletLoggerRecorder {

    public final static Logger log = LoggerFactory.getLogger(LoggerFilter.class);

    private ServletLoggerRecorder() {
    }

    public static String preRecord(LoggerProperties properties, HttpServletRequest request) {
        final StringBuilder logRecord = new StringBuilder(256);
        int level = properties.getLevel();
        // basic
        final String requestMethod = request.getMethod();
        if (LoggerLevel.enable(level, LoggerLevel.REQUEST_BASIC)) {
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
        if (LoggerLevel.enable(level, LoggerLevel.REQUEST_HEADER)) {
            String header = Collections.list(request.getHeaderNames())
                    .stream()
                    .map(name -> name + ": " + request.getHeader(name))
                    .collect(Collectors.joining("\n", "\n", ""));
            if (StringUtils.hasText(header)) {
                logRecord.append(header);
            }
        }
        // body
        if (LoggerLevel.enable(level, LoggerLevel.RESPONSE_BODY)) {
            if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
                logRecord.append("\n\n")
                        .append(readerBody(request));
            }
        }
        return logRecord.toString();
    }

    public static String readerBody(HttpServletRequest request) {
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

    public static String postRecord(LoggerProperties properties,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    Exception ex) {
        final StringBuilder logRecord = new StringBuilder(256);
        int level = properties.getLevel();
        // basic
        if (LoggerLevel.enable(level, LoggerLevel.RESPONSE_BASIC)) {
            logRecord.append(request.getMethod())
                    .append(" ")
                    .append(request.getRequestURL())
                    .append(" \n")
                    .append(request.getProtocol())
                    .append(" ")
                    .append(ex != null ? 500 : response.getStatus());
        }
        // header
        if (LoggerLevel.enable(level, LoggerLevel.RESPONSE_HEADER)) {
            String header = response.getHeaderNames()
                    .stream()
                    .map(name -> name + ": " + response.getHeader(name))
                    .collect(Collectors.joining("\n", "\n", ""));
            if (StringUtils.hasText(header)) {
                logRecord.append(header);
            }
        }
        // body
        if (ex == null) {
            if (LoggerLevel.enable(level, LoggerLevel.RESPONSE_BODY)) {
                if (response instanceof ResponseRepeatable) {
                    logRecord.append("\n\n")
                            .append(String.copyValueOf(
                                    new String(((ResponseRepeatable) response).responseBody()).toCharArray()));
                }
            }
            // error
        } else {
            if (LoggerLevel.enable(level, LoggerLevel.RESPONSE_ERROR)) {
                logRecord.append("\n\n")
                        .append("发生异常: ")
                        .append(ex.getMessage());
            }
        }
        return logRecord.toString();
    }

    public static void stepRecords(LoggerProperties properties, HttpServletRequest request,
                                   HttpServletResponse response, FilterRunnable run)
            throws IOException, ServletException {
        String requestLog = "\n<================  Request Start  ================>\n"
                + preRecord(properties, request)
                + "\n<=================  Request End  =================>\n";
        log.info(requestLog);
        long start = System.currentTimeMillis();
        StringBuilder responseLog = new StringBuilder()
                .append("\n<================  Response Start  ================>\n");
        try {
            run.run();
            responseLog.append(postRecord(properties, request, response, null));
        } catch (Exception e) {
            responseLog.append(postRecord(properties, request, response, e));
            throw e;
        } finally {
            log.info(responseLog.append("\nExecution cost：")
                    .append(System.currentTimeMillis() - start)
                    .append("ms\n<=================  Response End  =================>\n")
                    .toString());
        }
    }

    public static void mergeRecords(LoggerProperties properties, HttpServletRequest request,
                                    HttpServletResponse response, FilterRunnable run)
            throws IOException, ServletException {
        StringBuilder logFormat = new StringBuilder()
                .append("\n==================== Request ====================\n")
                .append(preRecord(properties, request))
                .append("\n==================== Response ===================\n");

        long start = System.currentTimeMillis();
        try {
            run.run();
            logFormat.append(postRecord(properties, request, response, null));
        } catch (Exception e) {
            logFormat.append(postRecord(properties, request, response, e));
            throw e;
        } finally {
            log.info(logFormat.append("\nExecution cost：")
                    .append(System.currentTimeMillis() - start)
                    .append(" ms")
                    .append("\n=================================================\n")
                    .toString());
        }
    }
}
