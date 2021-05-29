package com.quan.demo.framework.spring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(Config actualConfig) {
        return Redisson.create(actualConfig);
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis", name = "enable", havingValue = "single", matchIfMissing = true)
    public Config useSingleServer(RedissonProperties properties) {
        Config config = new Config();
        config.useSingleServer()
                .setPassword(properties.getPassword())
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis", name = "enable", havingValue = "cluster")
    public Config useClusterServer(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getCluster(), "请配置spring.redis.cluster");
        config.useClusterServers()
                .setPassword(properties.getPassword())
                .getNodeAddresses().addAll(properties.getCluster().getNodes());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis", name = "enable", havingValue = "sentinel")
    public Config useSentinelServer(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getSentinel(), "请配置spring.redis.sentinel");
        config.useSentinelServers()
                // 暂时关闭检查
                .setCheckSentinelsList(false)
                .setPassword(properties.getPassword())
                .setMasterName(properties.getSentinel().getMaster())
                .getSentinelAddresses().addAll(properties.getSentinel().getNodes());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis", name = "enable", havingValue = "master-slave")
    public Config useMasterSlaveServers(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getSentinel(), "请配置spring.redis.master-slave");
        config.useMasterSlaveServers()
                .setPassword(properties.getPassword())
                // 通用sentinel的配置
                .setMasterAddress(properties.getSentinel().getMaster())
                .getSlaveAddresses().addAll(properties.getSentinel().getNodes());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = "spring.redis", name = "enable", havingValue = "replicated")
    public Config useReplicatedServers(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getCluster(), "请配置spring.redis.replicated");
        config.useReplicatedServers()
                .setPassword(properties.getPassword())
                // 通用cluster的配置
                .getNodeAddresses().addAll(properties.getCluster().getNodes());
        return config;
    }
}
