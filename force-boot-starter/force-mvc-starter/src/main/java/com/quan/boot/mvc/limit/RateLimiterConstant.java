package com.quan.boot.mvc.limit;

/**
 * @author Force-oneself
 * @date 2023-10-10
 */
public interface RateLimiterConstant {

    /**
     * 计数统计
     */
    String LOCAL_COUNTER = "local-counter";

    /**
     * 令牌桶
     */
    String LOCAL_TOKEN_BUCKET = "local-token-bucket";

    /**
     * 漏桶
     */
    String LOCAL_LEAKY_BUCKET = "local-leaky-bucket";

    /**
     * 滑动窗口
     */
    String LOCAL_SLIDING_WINDOW = "local-sliding-window";

    /**
     * 默认
     */
    String LIMIT_DEFAULT = "default";

}
