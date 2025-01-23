package com.quan.cache.config;

import com.quan.cache.core.CacheService;
import com.quan.cache.properties.LocalCacheProperties;
import com.quan.cache.support.local.CaffeineLocalCacheService;
import com.quan.cache.support.MultiLevelCacheService;
import com.quan.cache.support.redis.RedissonCacheService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
@Configuration
@EnableConfigurationProperties(LocalCacheProperties.class)
public class ForceCacheConfiguration {

    @Bean
    public CacheService localCacheService(LocalCacheProperties localCacheProperties) {
        return new CaffeineLocalCacheService(localCacheProperties);
    }

    @Bean
    public CacheService redissonCacheService(RedissonClient redissonClient) {
        return new RedissonCacheService(redissonClient);
    }

    @Bean
    public CacheService multiLevelCacheService(CacheService localCacheService, CacheService redissonCacheService) {
        return new MultiLevelCacheService(localCacheService, redissonCacheService);
    }
}
