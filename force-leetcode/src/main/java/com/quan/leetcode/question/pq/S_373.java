package com.quan.leetcode.question.pq;

import java.util.*;

/**
 * S_373
 *
 * @author Force-oneself
 * @date 2022-05-07
 */
public class S_373 {

    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] + o1[1] - o2[0] - o2[1];
            }
        });
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (queue.size() >= k) {
                    int sum = num1 + num2;
                } else {
                    queue.add(new int[]{num1, num2});
                }
            }
        }
        List<List<Integer>> ant = new ArrayList<>();
        int size = queue.size();
        for (int i = 0; i < k && i < size; i++) {
            List<Integer> row = new ArrayList<>();
            int[] cur = queue.poll();
            row.add(cur[0]);
            row.add(cur[1]);
            ant.add(row);
        }
        return ant;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_373().kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(new S_373().kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        System.out.println(new S_373().kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
    }
}
