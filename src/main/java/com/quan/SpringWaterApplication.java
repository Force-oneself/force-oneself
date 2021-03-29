package com.quan;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @Description: SpringBoot 入口
 * @Author heyq
 * @Date 2021-01-29
 **/
@SpringBootApplication(exclude = {
        ElasticsearchAutoConfiguration.class,
        RestClientAutoConfiguration.class,
        Neo4jRepositoriesAutoConfiguration.class,
        Neo4jDataAutoConfiguration.class,
        RedisAutoConfiguration.class,
        RedissonAutoConfiguration.class,
        FreeMarkerAutoConfiguration.class,
        RabbitAutoConfiguration.class,
        MongoAutoConfiguration.class,
        KafkaAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class,
        DataSourceAutoConfiguration.class
})
@EnableEncryptableProperties
@EnableAspectJAutoProxy
public class SpringWaterApplication {

    public static void main(String[] args) {
        // 启动参数设置,比如自动生成端口
//        new StartCommand(args);
        // es版本对应报错问题
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringWaterApplication.class, args);
    }
}
