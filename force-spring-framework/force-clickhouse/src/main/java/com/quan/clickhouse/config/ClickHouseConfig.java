package com.quan.clickhouse.config;

import com.clickhouse.client.config.ClickHouseClientOption;
import com.clickhouse.client.config.ClickHouseDefaults;
import com.clickhouse.data.ClickHouseFormat;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.quan.clickhouse.properties.ClickHouseProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.quan.clickhouse.mapper")
@EnableConfigurationProperties(ClickHouseProperties.class)
public class ClickHouseConfig {

    @Bean
    public DataSource dataSource(ClickHouseProperties properties) throws SQLException {
        Properties prop = new Properties();
        prop.setProperty(ClickHouseDefaults.USER.getKey(), properties.getUser());
        prop.setProperty(ClickHouseDefaults.PASSWORD.getKey(), properties.getPassword());
        prop.setProperty(ClickHouseClientOption.SSL.getKey(), properties.getSsl());

        prop.setProperty("cluster", properties.getCluster());
        prop.setProperty("replica_num", properties.getReplicaNumber());
        prop.setProperty("shard_num", properties.getShardNumber());
        prop.setProperty("shard_weight", properties.getShardWeight());
        prop.setProperty("weight", properties.getWeight());

        prop.setProperty(ClickHouseClientOption.ASYNC.getKey(), Boolean.FALSE.toString());
        prop.setProperty(ClickHouseClientOption.FORMAT.getKey(), ClickHouseFormat.RowBinaryWithNamesAndTypes.name());
        prop.setProperty(ClickHouseClientOption.PRODUCT_NAME.getKey(), "ClickHouse-JdbcDriver");
        return new ClickHouseDataSource(properties.getUrl(), prop);
    }
}
