package com.quan.framework.spring.controller;

import com.quan.boot.mvc.crypto.annotation.Decrypt;
import com.quan.boot.mvc.crypto.annotation.Encrypt;
import com.quan.boot.mvc.crypto.annotation.RSADecrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Force-oneself
 * @Description DemoController
 * @date 2021-08-24
 */
@RestController
public class DemoController {

    @GetMapping("/get")
    public String get(String name, String say) {
        return name + say;
    }
    @PostMapping("/post")
    public Demo post(@RequestBody Demo demo) {
        return demo;
    }

    @PostMapping("/error")
    public Demo error(@RequestBody Demo demo) {
        int i = 1/0;
        return demo;
    }

    @GetMapping("/decrypt")
    @Encrypt
    public String get(@Decrypt String name) {
        return name;
    }


    @GetMapping("/decryptBody")
    @Encrypt
    @Decrypt
    public Demo decryptBody(@RequestBody Demo demo) {
        demo.setSay(demo.getSay() + " 加密测试");
        return demo;
    }

    @GetMapping("/decrypt/rsa")
    @RSADecrypt
    public Demo rsa(@RequestBody Demo demo) {
        demo.setSay(demo.getSay() + " 加密测试");
        return demo;
    }

    @GetMapping("/decrypt/param/entity")
    public String decrypt(ParamEntity entity) {
        return entity.name;
    }


    public static class Demo {
        private String name;
        private String say;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSay() {
            return say;
        }

        public void setSay(String say) {
            this.say = say;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "name='" + name + '\'' +
                    ", say='" + say + '\'' +
                    '}';
        }
    }


    public static class ParamEntity{
        private String name;
        private String say;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSay() {
            return say;
        }

        public void setSay(String say) {
            this.say = say;
        }
    }
}
