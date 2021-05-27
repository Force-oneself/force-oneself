package com.quan.demo.framework.spring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @Description: class RedissonConfig
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
@Configuration
@ConditionalOnMissingBean(RedissonClient.class)
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(Config actualConfig) {
        return Redisson.create(actualConfig);
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis.single", name = "enable", havingValue = "true", matchIfMissing = true)
    public Config useSingleServer(RedisProperties properties) {
        String host = properties.getHost();
        int port = properties.getPort();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis.cluster", name = "enable", havingValue = "true")
    public Config useClusterServer(RedisProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getCluster(), "请配置spring.redis.cluster");
        config.useClusterServers().getNodeAddresses().addAll(properties.getCluster().getNodes());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis.sentinel", name = "enable", havingValue = "true")
    public Config useSentinelServer(RedisProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getSentinel(), "请配置spring.redis.sentinel");
        config.useSentinelServers()
                .setMasterName(properties.getSentinel().getMaster())
                .getSentinelAddresses().addAll(properties.getSentinel().getNodes());
        return config;
    }
}
