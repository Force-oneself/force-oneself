package com.quan.leetcode.question.string;

import java.util.Arrays;

/**
 * S_434
 *
 * @author Force-oneself
 * @date 2022-05-26
 */
public class S_434 {

    public int countSegments(String s) {
        s = s.trim();
        int len = s.length();
        if (len < 1) {
            return 0;
        }
        return (int) Arrays.stream(s.split(" ")).filter(obj -> !"".equals(obj)).count();
    }

    public static void main(String[] args) {
        System.out.println(new S_434().countSegments("Hello, my name is John"));
        System.out.println(new S_434().countSegments(""));
    }
}
