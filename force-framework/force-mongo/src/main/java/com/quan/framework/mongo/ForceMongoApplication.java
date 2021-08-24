package com.quan.framework.mongo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
　* @Description ForceMongoApplication.java
　* @author Force-oneself
　* @date 2021-06-09 9:46
　**/
@SpringBootApplication
@EnableEncryptableProperties
public class ForceMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForceMongoApplication.class, args);
    }

}
