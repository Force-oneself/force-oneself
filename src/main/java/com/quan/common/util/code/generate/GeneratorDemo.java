package com.quan.common.util.code.generate;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-01
 **/
public class GeneratorDemo {

    public static void main(String[] args) {
        Stream<Integer> integerStream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(integerStream.reduce((x, y) -> x));
//        integerStream.reduce(Integer::max).ifPresent(System.out::println);
    }
}
