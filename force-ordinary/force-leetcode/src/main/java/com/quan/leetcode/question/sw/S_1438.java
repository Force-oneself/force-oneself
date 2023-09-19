package com.quan.leetcode.question.sw;

import java.util.TreeMap;

/**
 * S_1438
 *
 * @author Force-oneself
 * @date 2022-05-03
 */
public class S_1438 {

    public int longestSubarray(int[] nums, int limit) {
        int l = 0;
        int r = 0;
        int len = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ant = 0;
        while (r < len) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                Integer count = map.get(nums[l]);
                if (count == 1) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], map.get(nums[l]) - 1);
                }
                l++;
            }
            ant = Math.max(ant, r - l + 1);
            r++;
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_1438().longestSubarray(new int[]{8,2,4,7}, 4));
        System.out.println(new S_1438().longestSubarray(new int[]{10,1,2,4,7,2}, 5));
        System.out.println(new S_1438().longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
    }
}
