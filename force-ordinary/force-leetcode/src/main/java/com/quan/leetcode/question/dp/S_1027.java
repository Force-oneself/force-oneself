package com.quan.leetcode.question.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * S_1027
 *
 * @author Force-oneself
 * @date 2022-04-22
 */
public class S_1027 {

    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Set<Integer> diffs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                diffs.add(nums[j] - nums[i]);
            }
        }
        int res = 2;
        for (int diff : diffs) {
            res = Math.max(res, ls(nums, diff));
        }
        return res;
    }

    public int ls(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 1;
        // 计算所有差值的等差数列 最长的值
        for (int j : arr) {
            int val = map.getOrDefault(j - difference, 0);
            map.put(j, val + 1);
            res = Math.max(res, val + 1);
        }
        return res;
    }

    public int longestArithSeqLength1(int[] nums) {
        int n = nums.length, ans = 0;
        int[][] dp = new int[n][1010];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 表示nums[i]与nums[j]以差为d构成等差数列
                int d = nums[i] - nums[j] + 500;
                // dp[i][d]表示：nums[i]以差为d能与前面的数构成的等差数列的长度
                dp[i][d] = Math.max(dp[i][d], dp[j][d] + 1);
                ans = Math.max(ans, dp[i][d]);
            }
        }
        return ans + 1;
    }

}
