package com.quan.demo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @Description: class TestController
 * @Author Force丶Oneself
 * @Date 2021-05-22
 **/
@RestController
@Validated
public class TestController {


    @GetMapping("/test")
    public User test(@RequestParam("p") String p) {
        User user = new User();
        user.setLike(p);
        user.setUsername(p);
        return user;
    }

    @GetMapping("/get")
    public User get(@Validated User user) {
        return user;
    }

    @PostMapping ("/requestBody")
    public User requestBody(@RequestBody User user) {
        return user;
    }

    public static class User {
        private Long id;
        @NotBlank
        private String username;

        private String like;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
