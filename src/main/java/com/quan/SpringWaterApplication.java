package com.quan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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
//        RedisAutoConfiguration.class,
//        RedissonAutoConfiguration.class
        DataSourceAutoConfiguration.class
})
@EnableEncryptableProperties
public class SpringWaterApplication {

    public static void main(String[] args) {
        // es版本对应报错问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringWaterApplication.class, args);
    }
}
