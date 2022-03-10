package com.quan.demo.algorithm;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description AlgorithmDemo
 * @date 2022-03-06
 */
public class AlgorithmDemo {


    public static void main(String[] args) {
        int[] ints = CountSort.sort(new int[]{1, 222, 44, 56, 77, 832, 32, 2, 4});
        Arrays.stream(ints).forEach(System.out::println);
    }



}
