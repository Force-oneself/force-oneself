package com.quan.leetcode.question.sw;

/**
 * S_2134
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_2134 {

    public int minSwaps(int[] nums) {
        // 分别用来记录数组内1总数、滑窗内1最大数、以及现在滑窗内1个数
        int count = 0, ans = 0, num = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            count += nums[i];
        }
        // 此时确定滑窗大小为count
        for (int i = 0; i < count; i++) {
            num += nums[i];
        }
        ans = num;
        for (int i = 0; i < len - 1; i++) {
            // (count + i) % len 环
            num += nums[(count + i) % len] - nums[i];
            ans = Math.max(ans, num);
        }
        return count - ans;
    }
}
