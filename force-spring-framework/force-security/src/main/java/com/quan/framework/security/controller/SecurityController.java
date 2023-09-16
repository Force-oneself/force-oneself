package com.quan.framework.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @Description SecurityController.java
 * @date 2021-07-26
 */
@RestController
public class SecurityController {


    @GetMapping("/hello")
    public String hello(){
        return "hello!";
    }
}
