package com.quan.boot.mvc.log;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * TranceId 设置
 *
 * @author Force-oneself
 * @date 2025-03-21
 */
public class LogTraceFilter extends OncePerRequestFilter {

    private static final String HEADER_TRANCE_ID = "Trance-Id";
    private static final String TRANCE_ID = "tranceId";

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tranceId = request.getHeader(HEADER_TRANCE_ID);
        MDC.put(TRANCE_ID, StringUtils.hasText(tranceId) ? tranceId : UUID.randomUUID().toString());
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TRANCE_ID);
        }
    }
}
