package com.quan.boot.mvc.controller;

import com.quan.boot.mvc.query.MvcEntityQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @date 2023-09-24
 */
@RestController
public class MvcControllerTest {

    @GetMapping
    @RequestMapping("limit")
    public String limit() {
        return " success";
    }

    @GetMapping
    @RequestMapping("desensitization")
    public MvcEntityQuery desensitization(MvcEntityQuery query) {
        return query;
    }

    @GetMapping
    @RequestMapping("decimal")
    public MvcEntityQuery decimal(MvcEntityQuery query) {
        return query;
    }
}
