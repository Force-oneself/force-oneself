package com.quan.leetcode.question.bs;

import java.util.TreeMap;

/**
 * @author Force-oneself
 * @description S_456
 * @date 2022-04-18
 */
public class S_456 {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                // 返回小于等于该数的最小值，+1 为了恰好大于该值
                Integer next = rightAll.ceilingKey(leftMin + 1);
                // 存在大于最小值元素存在并小于中间的值
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            // 让较小的加入
            leftMin = Math.min(leftMin, nums[j]);
            // 将右边的栈中去掉下一个需要遍历的数
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }
        return false;
    }

}
