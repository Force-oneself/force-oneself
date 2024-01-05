package com.quan.demo;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.quan.demo.boot.listener.SpringApplicationName;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * @Description DemoApplication.java
 * @Author Force-oneself
 * @Date 2021-06-08 21:55
 **/
@SpringBootApplication(exclude = {RedissonAutoConfiguration.class, RedisAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class})
@SpringApplicationName(name = "force-demo-ann")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
