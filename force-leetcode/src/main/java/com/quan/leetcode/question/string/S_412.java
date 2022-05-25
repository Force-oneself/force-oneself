package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.List;

/**
 * S_412
 *
 * @author Force-oneself
 * @date 2022-05-25
 */
public class S_412 {

    public List<String> fizzBuzz(int n) {
        List<String>    ant = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            boolean five = i % 5 == 0;
            boolean three = i % 3 == 0;
            if (three && five) {
                ant.add("FizzBuzz");
            } else if (five) {
                ant.add("Buzz");
            } else if (three) {
                ant.add("Fizz");
            } else {
                ant.add(String.valueOf(i));
            }
        }
        return ant;
    }

    public static void main(String[] args) {
        new S_412().fizzBuzz(5).forEach(System.out::println);
    }
}
