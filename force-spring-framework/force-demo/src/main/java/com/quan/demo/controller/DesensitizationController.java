package com.quan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @date 2023-09-23
 */
@RestController
@RequestMapping("desensitization")
public class DesensitizationController {


    @GetMapping
    public User user(@RequestBody User user) {
        return user;
    }
}
