package com.quan.boot.controller;

import com.quan.boot.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2024-12-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String user(User user) {
        return user.toString();
    }

    @PostMapping
    public String postUser(@RequestBody User user) {
        return user.toString();
    }
}
