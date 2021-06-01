package com.quan.demo.framework.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.config.ReplicatedServersConfig;
import org.redisson.config.SentinelServersConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @Description: class RedissonProperties
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = RedissonConstant.PROPERTIES_PREFIX)
public class RedissonProperties {


    /**
     * Database index used by the connection factory.
     */
    private int database = 0;

    /**
     * Connection URL. Overrides host, port, and password. User is ignored. Example:
     * redis://user:password@example.com:6379
     */
    private String url;

    /**
     * Redis server host.
     */
    private String host = "localhost";

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = 6379;

    /**
     * Whether to enable SSL support.
     */
    private boolean ssl;

    /**
     * Connection timeout.
     */
    private Duration timeout;

    /**
     * Client name to be set on connections with CLIENT SETNAME.
     */
    private String clientName;

    private SentinelServersConfig sentinel;

    private ClusterServersConfig cluster;

    private MasterSlaveServersConfig masterSlave;

    private ReplicatedServersConfig replicated;

    private String enable;

}
