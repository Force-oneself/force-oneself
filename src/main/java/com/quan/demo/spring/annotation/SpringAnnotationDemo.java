package com.quan.demo.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-04-03
 **/
public class SpringAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext aca = new AnnotationConfigApplicationContext(Config.class);
    }
}
