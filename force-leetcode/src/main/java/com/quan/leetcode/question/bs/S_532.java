package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_532
 * @date 2022-04-19
 */
public class S_532 {


    public int findPairs(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int l = 0, r = 1;
        int last = Integer.MIN_VALUE;
        int ans = 0;
        while (r < len) {
            if (nums[r] != last) {
                int abs = Math.abs(nums[r] - nums[l]);
                // 满足条件
                if (abs == k) {
                    ans++;
                    last = nums[r];
                    l++;
                    r++;
                    // 差值大于k且左边指针++还在r的左边即可以增加l获取较小abs的值
                } else if (abs > k && l + 1 < r) {
                    l++;
                    // 否则增加r的获取较大的abs的值
                } else {
                    r++;
                }
                // 等于last说明上一个符合条件的数和当前数相等，上个数已经计算过跳过
            } else {
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_532().findPairs(new int[]{3, 1, 4, 1, 5}, 2));
    }
}
