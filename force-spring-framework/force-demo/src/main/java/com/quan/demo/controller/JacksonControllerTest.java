package com.quan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JacksonControllerTest
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
@RestController
@RequestMapping("/jackson")
public class JacksonControllerTest {

    @GetMapping
    public User requestParam(User user) {
        return user;
    }
}
