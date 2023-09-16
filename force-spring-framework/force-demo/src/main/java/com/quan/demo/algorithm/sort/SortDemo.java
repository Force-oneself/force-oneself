package com.quan.demo.algorithm.sort;

import java.util.Arrays;

/**
 * SortDemo
 *
 * @author Force-oneself
 * @date 2022-03-06
 */
public class SortDemo {

    public static void main(String[] args) {
        int[] ints = CountSort.sort(new int[]{1, 222, 44, 56, 77, 832, 32, 2, 4});
        Arrays.stream(ints).forEach(System.out::println);
    }

}
