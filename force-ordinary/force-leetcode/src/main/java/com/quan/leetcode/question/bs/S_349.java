package com.quan.leetcode.question.bs;

import java.util.*;

/**
 * @author Force-oneself
 * @description S_349
 * @date 2022-04-16
 */
public class S_349 {


    /**
     * 排序+双指针
     *
     * @param nums1 nums1
     * @param nums2 nums2
     * @return  return
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    /**
     * 排序+遍历二分
     *
     * @param nums1 nums1
     * @param nums2 nums2
     * @return  return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length;
        int len2 = nums2.length;

        int[] less = len1 >= len2 ? nums1 : nums2;
        int[] more = len1 < len2 ? nums1 : nums2;
        int len = more.length;
        Set<Integer> ant = new HashSet<>();
        for (int j : less) {
            if (!(j >= more[0] && j <= more[len - 1])) {
                continue;
            }
            int l = 0;
            int r = len - 1;
            while (l <= r) {
                int mid = (r - l) / 2 + l;
                if (more[mid] == j) {
                    ant.add(more[mid]);
                    break;
                } else if (more[mid] > j) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        int[] res = new int[ant.size()];
        int i = 0;
        for (Integer a : ant) {
            res[i++] = a;
        }
        return res;
    }
}
