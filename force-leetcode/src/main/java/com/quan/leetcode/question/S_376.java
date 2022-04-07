package com.quan.leetcode.question;

/**
 * @author Force-oneself
 * @description S_376
 * @date 2022-04-07
 */
public class S_376 {

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            // 升序
            if ((diff > 0 && prevdiff <= 0)
                    // 降序
                    || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }
}
