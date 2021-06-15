package com.quan.mongo.controller;

import com.quan.mongo.demo.MongoDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @Description TestController.java
 * @date 2021-06-09
 */
@RestController
public class TestController {

    @Autowired
    private MongoDemo mongoDemo;

    @GetMapping("/test")
    public void test() {
        mongoDemo.test();
    }
}
