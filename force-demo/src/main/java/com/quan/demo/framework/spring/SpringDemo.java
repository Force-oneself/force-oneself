package com.quan.demo.framework.spring;

import com.quan.demo.framework.spring.xml.BeanConfig;
import com.quan.demo.framework.spring.xml.SpringBeanExpandLifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-03
 **/
public class SpringDemo {

    public static void main(String[] args) {
//        ClassPathJsonApplicationContext context = new ClassPathJsonApplicationContext("reader.json");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml-reader.xml");
        GenericApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        SpringBeanExpandLifecycle bean = context.getBean(SpringBeanExpandLifecycle.class);
        System.out.println(bean);

//
//        List<BeanDefinition> collect = Arrays.stream(context.getBeanDefinitionNames())
//                .map(context::getBeanDefinition)
//                .collect(Collectors.toList());
//        collect.stream()
//                .map(JSONUtil::parse)
//                .map(JSONUtil::toJsonStr)
//                .forEach(System.out::println);

    }

}
