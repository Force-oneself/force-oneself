package com.quan.framework.spring.controller;

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
    public String post(@RequestBody Demo demo) {
        return demo.toString();
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

}
