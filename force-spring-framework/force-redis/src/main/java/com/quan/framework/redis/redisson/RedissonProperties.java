package com.quan.framework.redis.redisson;

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

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public SentinelServersConfig getSentinel() {
        return sentinel;
    }

    public void setSentinel(SentinelServersConfig sentinel) {
        this.sentinel = sentinel;
    }

    public ClusterServersConfig getCluster() {
        return cluster;
    }

    public void setCluster(ClusterServersConfig cluster) {
        this.cluster = cluster;
    }

    public MasterSlaveServersConfig getMasterSlave() {
        return masterSlave;
    }

    public void setMasterSlave(MasterSlaveServersConfig masterSlave) {
        this.masterSlave = masterSlave;
    }

    public ReplicatedServersConfig getReplicated() {
        return replicated;
    }

    public void setReplicated(ReplicatedServersConfig replicated) {
        this.replicated = replicated;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
