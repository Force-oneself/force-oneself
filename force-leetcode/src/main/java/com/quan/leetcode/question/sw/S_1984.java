package com.quan.leetcode.question.sw;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * S_1984
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_1984 {

    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int r = 0;
        int l = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ant = Integer.MAX_VALUE;
        int size = 0;
        while (r < len) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            size++;
            while (size > k) {
                Integer count = map.get(nums[l]);
                if (count == 1) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], count - 1);
                }
                size--;
                l++;
            }
            if (size == k) {
                ant = Math.min(ant, map.lastKey() - map.firstKey());
            }
            r++;
        }
        return ant;
    }

    /**
     * 排序
     *
     * @param nums nums
     * @param k    k
     * @return return
     */
    public int minimumDifference1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < n; ++i) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_1984().minimumDifference(new int[]{90}, 1));
        System.out.println(new S_1984().minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }
}
