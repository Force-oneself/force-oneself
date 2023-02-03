package com.quan.demo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * class TestController
 * @author Force丶Oneself
 * @date 2021-05-22
 **/
@RestController
public class TestController {


    /**
     * p==null ==> MissingServletRequestParameterException
     *
     * @param p p
     * @return  /
     */
    @GetMapping("/anno/requestParam")
    public User requestParam(@RequestParam("p") String p) {
        User user = new User();
        user.setLike(p);
        user.setUsername(p);
        return user;
    }

    @GetMapping("/get")
    public User get(@Validated User user) {
        return user;
    }

    /**
     * user == null ==> HttpMessageNotReadableException
     * user.username == null ==> MethodArgumentNotValidException
     *
     * @param user user
     * @return  /
     */
    @PostMapping ("/requestBody")
    public User requestBody(@Validxxx @RequestBody User user) {
        return user;
    }

}
