package com.quan.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-02-01
 **/
public class JvmDemo {

    private byte[] data = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {

        List<JvmDemo> list = new ArrayList<>();
        while (true) {
            list.add(new JvmDemo());
            Thread.sleep(50);
        }
    }
}
