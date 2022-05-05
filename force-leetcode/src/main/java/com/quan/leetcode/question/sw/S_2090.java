package com.quan.leetcode.question.sw;

import java.util.Arrays;

/**
 * S_2090
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_2090 {

    public int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        int[] ant = new int[len];
        Arrays.fill(ant, -1);
        int width = 2 * k;
        if (len <= width) {
            return ant;
        }

        long sum = 0;
        for (int i = 0; i < width; i++) {
            sum += nums[i];
        }

        for (int i = width; i < len; i++) {
            sum += nums[i];
            ant[i - k] = (int) (sum / (width + 1));
            sum -= nums[i - width];
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S_2090().getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3)));
        System.out.println(Arrays.toString(new S_2090().getAverages(new int[]{100000}, 0)));
        System.out.println(Arrays.toString(new S_2090().getAverages(new int[]{8}, 100000)));
    }
}
