package com.quan.boot.mvc.constant;

import org.springframework.core.Ordered;

/**
 * Filter 全局执行顺序常量
 *
 * @author Force-oneself
 * @date 2023-09-26
 */
public interface FilterOrdered {

    /**
     * Filter全局异常
     */
    int EXCEPTION = Ordered.HIGHEST_PRECEDENCE + 50;

    /**
     * 可重复读Servlet流
     */
    int REPEATABLE = Ordered.HIGHEST_PRECEDENCE + 100;

    /**
     * XSS
     */
    int xss = Ordered.HIGHEST_PRECEDENCE + 150;

    /**
     * 日志
     */
    int LOGGER = Ordered.HIGHEST_PRECEDENCE + 200;

    /**
     * 限流
     */
    int RATE_LIMIT = Ordered.HIGHEST_PRECEDENCE + 201;

}
