package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_633
 * @date 2022-04-19
 */
public class S_633 {

    public boolean judgeSquareSum(int c) {
        long l = 0;
        long r = (long) Math.sqrt(c);
        while (l <= r) {
            long square = l * l + r * r;
            if (c == square) {
                return true;
            } else if (c > square) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }
}
