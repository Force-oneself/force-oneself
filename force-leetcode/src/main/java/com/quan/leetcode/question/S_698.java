package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_698
 * @date 2022-04-13
 */
public class S_698 {

    enum Result {TRUE, FALSE}

    //nums下标、剩余的nums的和、memo数组、nums数组、每组的和
    // used 是整形、有32位， 而nums长度最长是16,所以used完全够用。used某一位是1代表nums[某一位]被用过
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            // 防止targ为0,所以求余之前每次减掉1、求余之后再加1,
            int targ = (todo - 1) % target + 1;
            // 先尝试放第1个数、如果成功就结束，如果不成功、就再尝试放第2个数，依次类推、直到最后一个数
            for (int i = 0; i < nums.length; i++) {
                // 这个位置的数没有用过、然后不大于target，就可用
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1 << i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        //nums数组的总和
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        // memo长度是2的nums.length次方
        Result[] memo = new Result[1 << nums.length];
        // 最后一个位置设为true
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }


    public boolean canPartitionKSubsets1(int[] nums, int k) {
        if (k == 1) {
            return true;
        }

        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 总和无法平均
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        // 如果最大的值大于平均值则无法进行均分
        if (nums[len - 1] > target) {
            return false;
        }

        int size = 1 << len;
        boolean[] dp = new boolean[size];
        dp[0] = true;
        int[] currentSum = new int[size];
        for (int i = 0; i < size; i++) {
            // 总是基于 dp[i] = true 的前提下进行状态转移
            if (!dp[i]) {
                continue;
            }

            // 基于当前状态，添加一个数以后
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }
                int next = i | (1 << j);
                if (dp[next]) {
                    continue;
                }
                if ((currentSum[i] % target) + nums[j] <= target) {
                    currentSum[next] = currentSum[i] + nums[j];
                    dp[next] = true;
                } else {
                    // 由于数组已经排好序，如果 (currentSum[i] % target) + nums[j] > target，剩下的数就没有必要枚举
                    break;
                }
            }
        }
        return dp[size - 1];
    }

}
