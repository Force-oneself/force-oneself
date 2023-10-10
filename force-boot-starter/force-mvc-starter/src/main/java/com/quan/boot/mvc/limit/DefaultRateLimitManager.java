package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.limit.local.*;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * 限流器对外服务默认实现
 *
 * @author Force-oneself
 * @date 2023-10-07
 */
public class DefaultRateLimitManager implements RateLimitManager {

    /**
     * 限流配置
     */
    private final RateLimitProperties properties;

    /**
     * 限流器缓存
     */
    private final Map<RateLimitPath, RateLimiter> rateLimiters = new ConcurrentHashMap<>(32);

    /**
     * 路径匹配器
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 限流器工厂路由器
     */
    private final Map<String, Function<RateLimitPath, RateLimiter>> rateLimiterFactoryRoute = new ConcurrentHashMap<>(16);

    public DefaultRateLimitManager(RateLimitProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        // 计数统计
        rateLimiterFactoryRoute.put(RateLimiterConstant.LOCAL_COUNTER, p -> new CounterRateLimiter(p.getTime(), p.getCapacity()));
        // 令牌桶
        rateLimiterFactoryRoute.put(RateLimiterConstant.LOCAL_TOKEN_BUCKET, p -> new TokenBucketRateLimiter(((double) (p.getCapacity() * 1000) / p.getTime())));
        // 漏桶
        rateLimiterFactoryRoute.put(RateLimiterConstant.LOCAL_LEAKY_BUCKET, p -> new LeakyBucketRateLimiter((int) (p.getCapacity() * 1000 / p.getTime()), p.getCapacity()));
        // 滑动窗口
        rateLimiterFactoryRoute.put(RateLimiterConstant.LOCAL_SLIDING_WINDOW, p -> new SlidingWindowRateLimiter(p.getTime(), p.getCapacity()));
        // 默认实现
        rateLimiterFactoryRoute.put(RateLimiterConstant.LIMIT_DEFAULT, p -> new SlidingWindowRateLimiter(p.getTime(), p.getCapacity()));
    }

    @Override
    public boolean limit(String url) {
        return properties.getPaths()
                .stream()
                .filter(obj -> this.realPath(obj, url))
                // 设置默认限流器
                .peek(path -> path.setKey(StringUtils.hasText(path.getKey()) ? path.getKey() : RateLimiterConstant.LIMIT_DEFAULT))
                .map(path -> rateLimiters.computeIfAbsent(path, p -> {
                    Function<RateLimitPath, RateLimiter> routeLimiter = rateLimiterFactoryRoute.get(p.getKey());
                    return routeLimiter != null ? routeLimiter.apply(p) : null;
                }))
                .filter(Objects::nonNull)
                .allMatch(RateLimiter::rateLimit);
    }

    private boolean realPath(RateLimitPath rateLimitPath, String url) {
        return antPathMatcher.match(rateLimitPath.getPath(), url);
    }
}
