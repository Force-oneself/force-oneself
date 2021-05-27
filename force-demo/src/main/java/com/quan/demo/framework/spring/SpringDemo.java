package com.quan.demo.framework.spring;

import cn.hutool.json.JSONUtil;
import com.quan.demo.framework.spring.context.ClassPathJsonApplicationContext;
import com.quan.demo.framework.spring.xml.BeanConfig;
import com.quan.demo.framework.spring.xml.SpringBeanExpandLifecycle;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringDemo {

    public static void main(String[] args) {


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
        GenericApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        print(Arrays.stream(context.getBeanDefinitionNames())
                .map(context::getBeanDefinition)
                .collect(Collectors.toList()));
    }


    public static void print(List<?> list) {
        list.stream()
                .map(JSONUtil::parse)
                .map(JSONUtil::toJsonStr)
                .forEach(System.out::println);
    }
}
