package com.quan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: class TestController
 * @Author Force丶Oneself
 * @Date 2021-05-22
 **/
@RestController
public class TestController {


    @GetMapping("/test")
    public String test(@RequestParam("p") String p) {
        return "Hello : " + p;
    }
}
