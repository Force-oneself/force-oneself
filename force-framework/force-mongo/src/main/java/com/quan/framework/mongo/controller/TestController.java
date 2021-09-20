package com.quan.framework.mongo.controller;

import com.quan.framework.mongo.spring.MongoDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Force-oneself
 * @Description TestController.java
 * @date 2021-06-09
 */
@RestController
public class TestController {

    private final MongoDemo mongoDemo;

    public TestController(MongoDemo mongoDemo) {
        this.mongoDemo = mongoDemo;
    }

    @PostMapping
    public void test() {
        mongoDemo.insert();
    }

    @GetMapping("/find")
    public void find(Integer type) {
        switch (type) {
            case 1:
                mongoDemo.find();
                break;
            case 2:
                mongoDemo.aggregation();
                break;
            default:
                break;
        }
    }

    @GetMapping("/mapParam")
    public Map<Double, Object> mapParam(@RequestParam Map<Double, Object> param) {
        param.get(33.0);
        return param;
    }

    @GetMapping("/defaultValue")
    public String defaultValue(@RequestParam(defaultValue = "dss") String param) {
        return param;
    }


    @PostMapping("/file")
    public String file(MultipartFile filename) {
        return filename.getName();
    }
}
