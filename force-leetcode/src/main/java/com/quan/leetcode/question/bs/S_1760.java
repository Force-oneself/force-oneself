package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1760
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public class S_1760 {

    public boolean check(int[] nums, long cost, int maxOperations) {
        long ans = 0;
        for (int cur : nums) {
            if (cur % cost == 0) {
                // 不需要均分
                ans += cur / cost - 1;
            } else {
                // 把大于该值的进行均分
                ans += cur / cost;
            }
        }
        return ans <= maxOperations;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int max = Arrays.stream(nums).max().getAsInt();
        long l = 1, r = max;

        long ret = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (check(nums, mid, maxOperations)) {
                r = mid - 1;
                ret = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) ret;
    }

}
