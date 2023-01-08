package com.quan.demo.limit.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;

import java.util.Collections;

/**
 * RedisTemplateSlidingWindowRateLimiter
 *
 * @author Force-oneself
 * @date 2023-01-08
 */
public class RedisTemplateSlidingWindowRateLimiter implements RedisRateLimiter {

    /**
     * --KEYS[1]: 限流 key
     * --ARGV[1]: 时间戳 - 时间窗口
     * --ARGV[2]: 当前时间戳（作为score）
     * --ARGV[3]: 阈值
     * --ARGV[4]: score 对应的唯一value
     * <p>
     * 1. 移除时间窗口之前的数据
     * 2. 统计当前元素数量
     * 3. 是否超过阈值
     */
    public static final String SCRIPT = "redis.call('zremrangeByScore', KEYS[1], 0, ARGV[1]) " +
            "local res = redis.call('zcard', KEYS[1]) " +
            "if (res == nil) or (res < tonumber(ARGV[3])) then " +
            "    redis.call('zadd', KEYS[1], ARGV[2], ARGV[4]) " +
            "    return 1 " +
            "else " +
            "    return 0 " +
            "end";

    private final String key;

    private final StringRedisTemplate redisTemplate;
    /**
     * 阈值
     */
    private final int threshold;
    /**
     * 统计窗口时间(毫秒)
     */
    private final long time;


    public RedisTemplateSlidingWindowRateLimiter(String key, StringRedisTemplate redisTemplate, int threshold, long time) {
        this.key = key;
        this.redisTemplate = redisTemplate;
        this.threshold = threshold;
        this.time = time;
    }

    @Override
    public boolean rateLimit() {
        long now = System.currentTimeMillis();
        String oldest = String.valueOf(now - time);
        String score = String.valueOf(now);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new StaticScriptSource(SCRIPT));
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), oldest, score, threshold, score);
        return result != null && result == 1;
    }
}
