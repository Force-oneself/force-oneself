package com.quan.demo.limit.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;

import java.util.Collections;

/**
 * RedisTemplateCounterRateLimiter
 *
 * @author Force-oneself
 * @date 2023-01-08
 */
public class RedisTemplateCounterRateLimiter implements RedisRateLimiter {

    public static final String SCRIPT = "local count = redis.call(\"incr\",KEYS[1]) " +
            "if count == 1 then " +
            "  redis.call('expire',KEYS[1],ARGV[2]) " +
            "end " +
            "if count > tonumber(ARGV[1]) then " +
            "  return 0 " +
            "end " +
            "return 1";

    private final String prefix;


    private final StringRedisTemplate redisTemplate;
    /**
     * 阈值
     */
    private final int threshold;
    /**
     * 统计窗口时间(毫秒)
     */
    private final long time;

    public RedisTemplateCounterRateLimiter(String prefix, StringRedisTemplate redisTemplate, int threshold, long time) {
        this.prefix = prefix;
        this.redisTemplate = redisTemplate;
        this.threshold = threshold;
        this.time = time;
    }

    @Override
    public boolean rateLimit() {
        String key = prefix + System.currentTimeMillis() / time;
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new StaticScriptSource(SCRIPT));
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(key), threshold, time);
        return result != null && result == 1;
    }
}
