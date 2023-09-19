package com.quan.leetcode.question.dp;

/**
 * S_377
 *
 * @author Force-oneself
 * @date 2022-05-17
 */
public class S_377 {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
