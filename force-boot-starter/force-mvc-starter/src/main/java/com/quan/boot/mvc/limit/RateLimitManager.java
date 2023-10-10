package com.quan.boot.mvc.limit;

/**
 * 限流器服务接口
 *
 * @author Force-oneself
 * @date 2023-10-07
 */
public interface RateLimitManager {

    /**
     * 判断路径是否被限流
     *
     * @param url 路径
     * @return 是否限流
     */
    boolean limit(String url);
}
