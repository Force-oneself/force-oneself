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
    public User test(@RequestParam("p") String p) {
        User user = new User();
        user.setLike(p);
        user.setUsername(p);
        return user;
    }

    public static class User {
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
    }
}
