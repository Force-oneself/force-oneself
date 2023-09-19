package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1283
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
public class S_1283 {

    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        int ans = -1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            int sum = 0;
            for (int num : nums) {
                sum += (num - 1) / mid + 1;
            }
            if (sum > threshold) {
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new S_1283().smallestDivisor(new int[]{1, 2, 5, 9}, 6));
        System.out.println(new S_1283().smallestDivisor(new int[]{2, 3, 5, 7, 11}, 11));
        System.out.println(new S_1283().smallestDivisor(new int[]{19}, 5));
    }
}
