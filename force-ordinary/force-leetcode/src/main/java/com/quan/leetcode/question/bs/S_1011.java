package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1011
 *
 * @author Force-oneself
 * @date 2022-04-22
 */
public class S_1011 {

    public int shipWithinDays(int[] weights, int days) {
        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();
        while (l < r) {
            int mid = (r - l) / 2 + l;
            int match = lessMatch(weights, mid, days);
            if (match >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int lessMatch(int[] weights, int mid, int days) {
        int row = mid;
        for (int weight : weights) {
            if (row >= 0 && row - weight < 0) {
                days--;
                row = mid;
            }
            row -= weight;
        }
        if (row >= 0) {
            days--;
        }
        return days;
    }

    public static void main(String[] args) {
        System.out.println(new S_1011().shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        System.out.println(new S_1011().shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
        System.out.println(new S_1011().shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
        System.out.println(new S_1011().shipWithinDays(new int[]{3,3,3,3,3,3}, 2));
    }
}
