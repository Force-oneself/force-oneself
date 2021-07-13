package com.quan.demo.other;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author Force-oneself
 * @Description JOLDemo.java
 * @date 2021-07-12
 */
public class JolDemo {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
    }
}
