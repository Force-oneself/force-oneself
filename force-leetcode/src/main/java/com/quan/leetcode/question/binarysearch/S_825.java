package com.quan.leetcode.question.binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * S_825
 *
 * @author Force-oneself
 * @date 2022-04-20
 */
public class S_825 {

    public int numFriendRequests(int[] ages) {
        int ant = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < ages.length; i++) {
            int row = 0;
            if (map.containsKey(ages[i])) {
                row = map.get(ages[i]);
            } else {
                for (int j = 0; j < ages.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    boolean match = (ages[j] <= ages[i] / 2 + 7) || ages[j] > ages[i] || (ages[j] > 100 && ages[i] < 100);
                    if (!match) {
                        row++;
                    }
                }
            }
            ant += row;
            map.put(ages[i], row);
        }
        return ant;
    }


    /**
     * 排序+双指针
     *
     * 0.5×ages[x]+7<ages[y]≤ages[x]
     *
     * @param ages ages
     * @return return
     */
    public int numFriendRequests1(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {
                ++right;
            }
            ans += right - left;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_825().numFriendRequests(new int[]{16, 16}));
        System.out.println(new S_825().numFriendRequests(new int[]{16, 17, 18}));
        System.out.println(new S_825().numFriendRequests(new int[]{20, 30, 100, 110, 120}));
    }
}
