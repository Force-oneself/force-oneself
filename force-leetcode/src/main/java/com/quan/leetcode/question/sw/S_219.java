package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * S_219
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public class S_219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                if (i - index <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * 滑动窗口
     *
     * @param nums nums
     * @param k    k
     * @return return
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 在k的窗口大小前面的数需要被排除出去
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new S_219().containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(new S_219().containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(new S_219().containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}
