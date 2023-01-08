package com.quan.demo.controller;

import com.quan.demo.framework.desensitization.annotation.Desensitization;
import com.quan.demo.framework.desensitization.config.DesensitizationAutoConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Description: class TestController
 * @Author Force丶Oneself
 * @Date 2021-05-22
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

    public static class User {

        private Long id;

        @NotBlank(message = "ddd")
        @Desensitization(ops = DesensitizationAutoConfiguration.SimpleOperation.class)
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
