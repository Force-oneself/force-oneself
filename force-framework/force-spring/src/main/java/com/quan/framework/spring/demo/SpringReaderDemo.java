package com.quan.framework.spring.demo;

import cn.hutool.json.JSONUtil;
import com.quan.framework.spring.config.MyApplicationContext;
import com.quan.framework.spring.expand.json.context.ClassPathJsonApplicationContext;
import com.quan.framework.spring.config.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringReaderDemo {

    public static void main(String[] args) {
//        xmlReader();
        annotationReader();
    }

    public static void xmlReader() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml-reader.xml");
        print(Arrays.stream(context.getBeanDefinitionNames())
                .map(obj -> context.getBeanFactory().getBeanDefinition(obj))
                .collect(Collectors.toList()));
    }

    public static void jsonReader() {
        ClassPathJsonApplicationContext context = new ClassPathJsonApplicationContext("reader.json");
        print(Arrays.stream(context.getBeanDefinitionNames())
                .map(obj -> context.getBeanFactory().getBeanDefinition(obj))
                .collect(Collectors.toList()));
    }

    public static void annotationReader() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
    }

    public static void myApplicationContext() {
        MyApplicationContext context = new MyApplicationContext(BeanConfig.class);
    }


    public static void print(List<?> list) {
        list.stream()
                .map(JSONUtil::parse)
                .map(JSONUtil::toJsonStr)
                .forEach(System.out::println);
    }

}
