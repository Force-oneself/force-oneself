package com.quan.leetcode.question.binarysearch;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_287
 * @date 2022-04-16
 */
public class S_287 {

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 快慢指针，存在一个数重复 即存在一个环
     *
     * @param nums nums
     * @return return
     */
    public int findDuplicate1(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
