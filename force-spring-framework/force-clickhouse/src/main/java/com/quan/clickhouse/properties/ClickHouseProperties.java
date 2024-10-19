package com.quan.clickhouse.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "force.clickhouse")
public class ClickHouseProperties {

    private String url = "jdbc:clickhouse://localhost:8123/default";

    private String user = "default";

    private String password = "";

    private String ssl = "";

    private String cluster = "";

    private String replicaNumber = "0";

    private String shardNumber = "0";

    private String shardWeight = "0";

    private String weight = "1";

    public String getSsl() {
        return ssl;
    }

    public void setSsl(String ssl) {
        this.ssl = ssl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getReplicaNumber() {
        return replicaNumber;
    }

    public void setReplicaNumber(String replicaNumber) {
        this.replicaNumber = replicaNumber;
    }

    public String getShardNumber() {
        return shardNumber;
    }

    public void setShardNumber(String shardNumber) {
        this.shardNumber = shardNumber;
    }

    public String getShardWeight() {
        return shardWeight;
    }

    public void setShardWeight(String shardWeight) {
        this.shardWeight = shardWeight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
