package com.quan.leetcode.question.bs;

/**
 * @author Force-oneself
 * @description S_167
 * @date 2022-04-15
 */
public class S_167 {

    public int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int l = 0;
        int r = len - 1;
        while (l < r) {
            int sum = numbers[r] + numbers[l];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }
}
