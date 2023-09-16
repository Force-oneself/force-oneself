package com.quan.demo.reflect;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

/**
 * @author Force-oneself
 * @description ReflectDemo
 * @date 2022-03-05
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        Field field = ReflectClass.class.getDeclaredField("df");
        AnnotatedType annotatedType = field.getAnnotatedType();
        System.out.println(field);
    }
}
