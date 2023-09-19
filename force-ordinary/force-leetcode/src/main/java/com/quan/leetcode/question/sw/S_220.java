package com.quan.leetcode.question.sw;

import java.util.TreeSet;

/**
 * S_220
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public class S_220 {

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j > k) {
                    continue;
                }
                if (Math.abs(nums[i] - nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            // 在k的窗口内存在一个值在 [nums[i] - t , nums[i] + t] 之间
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 加入窗口元素内
            set.add((long) nums[i]);
            if (i >= k) {
                // 删除k窗口宽度前的元素
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_220().containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(new S_220().containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(new S_220().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

}
