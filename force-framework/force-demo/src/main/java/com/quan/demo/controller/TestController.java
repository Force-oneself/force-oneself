package com.quan.demo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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


    @GetMapping("/ssp")
    public String sso(@RequestParam("p") String p) {
        System.out.println(p);
        return p;
    }

    @GetMapping("/sso")
    public String sso(MultipartFile file) throws IOException {
        String pathname = "/Users/force-oneself/Desktop/sso.txt";
        file.transferTo(new File(pathname));
        return "ok";
    }
}
