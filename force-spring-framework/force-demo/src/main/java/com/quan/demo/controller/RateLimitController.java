package com.quan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
@RestController
@RequestMapping("limit")
public class RateLimitController {


    @GetMapping
    public String limit() {
        return "success";
    }
}
