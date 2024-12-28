package com.quan.lock.config;

import com.quan.lock.LockService;
import com.quan.lock.support.RedissonLockService;
import com.quan.lock.support.StandAloneLockService;
import com.quan.lock.properties.LockProperties;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.quan.lock.properties.LockProperties.PREFIX;

/**
 * Lock 自动装配
 *
 * @author Force-oneself
 * @date 2024-12-26
 */
@ConditionalOnProperty(prefix = PREFIX, value = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(LockProperties.class)
public class ForceLockConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(RedissonClient.class)
    public LockService lockService(RedissonClient redissonClient) {
        return new RedissonLockService(redissonClient);
    }


    @ConditionalOnMissingBean
    @Bean
    public LockService standAloneLockService() {
        return new StandAloneLockService();
    }
}
