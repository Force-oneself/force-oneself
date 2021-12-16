package com.quan.demo.stream;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @description ConcurrentDemo
 * @date 2021-12-14
 */
public class ConcurrentDemo {

    private final static Logger log = LoggerFactory.getLogger(ConcurrentDemo.class);

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
//
//        long start = System.currentTimeMillis();
        ListUtils.partition(list, 100).stream().map(ArrayList::new).collect(Collectors.toSet());
//
//        ListUtils.partition(Collections.synchronizedList(list), 100).stream().parallel().forEach(obj -> log.info("{}", obj));
//        System.out.println(System.currentTimeMillis() - start);
        List<Integer> integers = Collections.synchronizedList(list);
        List<Integer> subList = integers.subList(0, 1);
        integers.add(222);
        System.out.println(subList.get(0));

    }
}
