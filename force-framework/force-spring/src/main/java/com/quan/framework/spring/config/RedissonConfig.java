package com.quan.framework.spring.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.ReplicatedServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

/**
 * @Description: class RedissonConfig
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
//@Configuration
@ConditionalOnMissingBean(RedissonClient.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(Config actualConfig) {
        return Redisson.create(actualConfig);
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = RedissonConstant.PROPERTIES_PREFIX, name = "enable", havingValue = "single", matchIfMissing = true)
    public Config useSingleServer(RedissonProperties properties) {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(properties.getDatabase())
                .setPassword(properties.getPassword())
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = RedissonConstant.PROPERTIES_PREFIX, name = "enable", havingValue = "cluster")
    public Config useClusterServer(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getCluster(), "请配置cluster");
        ClusterServersConfig cluster = properties.getCluster();
        config.useClusterServers()
                .setPassword(cluster.getPassword())
                .getNodeAddresses().addAll(cluster.getNodeAddresses());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = RedissonConstant.PROPERTIES_PREFIX, name = "enable", havingValue = "sentinel")
    public Config useSentinelServer(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getSentinel(), "请配置sentinel");
        SentinelServersConfig sentinel = properties.getSentinel();
        config.useSentinelServers()
                .setDatabase(sentinel.getDatabase())
                .setCheckSentinelsList(sentinel.isCheckSentinelsList())
                .setPassword(sentinel.getPassword())
                .setMasterName(sentinel.getMasterName())
                .getSentinelAddresses().addAll(sentinel.getSentinelAddresses());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = RedissonConstant.PROPERTIES_PREFIX, name = "enable", havingValue = "masterSlave")
    public Config useMasterSlaveServers(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getSentinel(), "请配置masterSlave");
        MasterSlaveServersConfig masterSlave = properties.getMasterSlave();
        config.useMasterSlaveServers()
                .setDatabase(masterSlave.getDatabase())
                .setPassword(masterSlave.getPassword())
                .setMasterAddress(masterSlave.getMasterAddress())
                .getSlaveAddresses().addAll(masterSlave.getSlaveAddresses());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    @ConditionalOnProperty(prefix = RedissonConstant.PROPERTIES_PREFIX, name = "enable", havingValue = "replicated")
    public Config useReplicatedServers(RedissonProperties properties) {
        Config config = new Config();
        Objects.requireNonNull(properties.getCluster(), "请配置replicated");
        ReplicatedServersConfig replicated = properties.getReplicated();
        config.useReplicatedServers()
                .setDatabase(replicated.getDatabase())
                .setPassword(replicated.getPassword())
                .setScanInterval(replicated.getScanInterval())
                .getNodeAddresses().addAll(replicated.getNodeAddresses());
        return config;
    }
}
