package com.quan.leetcode.question.sw;


import java.util.Arrays;

/**
 * S_1838
 *
 * @author Force-oneself
 * @date 2022-05-04
 */
public class S_1838 {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            // 计算前面元素填补到num[r]所需要到多少数
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            // 填补的数超出阈值，l则右移
            while (total > k) {
                // nums[r] - nums[l] 为l填补到r的缺的数
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
