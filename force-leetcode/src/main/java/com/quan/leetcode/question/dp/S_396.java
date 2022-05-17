package com.quan.leetcode.question.dp;

import java.util.Arrays;

/**
 * S_396
 *
 * @author Force-oneself
 * @date 2022-05-17
 */
public class S_396 {

    public int maxRotateFunction(int[] nums) {
        int f = 0, n = nums.length, numSum = Arrays.stream(nums).sum();
        // F(0)
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        // F(1) = F(0) + sum - n * nums[n-1]
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new S_396().maxRotateFunction(new int[]{4, 3, 2, 6}));
    }
}
