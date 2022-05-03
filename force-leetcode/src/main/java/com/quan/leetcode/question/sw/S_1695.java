package com.quan.leetcode.question.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * S_1695
 *
 * @author Force-oneself
 * @date 2022-05-03
 */
public class S_1695 {

    public int maximumUniqueSubarray1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        int len = nums.length;
        int ant = 0;
        int s = 0, e = 0;
        while (r < len) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.get(nums[r]) > 1) {
                Integer count = map.get(nums[l]);
                if (count == 1) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], map.get(nums[l]) - 1);
                }
                l++;
            }
            int width = r - l + 1;
            if (width > ant) {
                s = l;
                e = r;
                ant = width;
            }
            r++;
        }
        int sum = 0;
        for (int i = s; i <= e; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, sum = 0, start = 0;
        for (int num : nums) {
            while (!set.isEmpty() && set.contains(num)) {
                sum -= nums[start];
                set.remove(nums[start]);
                start++;
            }
            set.add(num);
            sum += num;
            max = Math.max(sum, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new S_1695().maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println(new S_1695().maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }
}
