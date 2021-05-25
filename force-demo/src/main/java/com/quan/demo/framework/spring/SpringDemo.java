package com.quan.demo.framework.spring;

import com.quan.demo.framework.spring.xml.BeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(BeanConfig.class);
        acac.getBeanFactory().getBeanNamesIterator().forEachRemaining(System.out::println);
    }
}
