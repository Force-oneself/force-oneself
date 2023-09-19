package com.quan.leetcode.question.pq;

import java.util.*;

/**
 * S_347
 *
 * @author Force-oneself
 * @date 2022-05-06
 */
public class S_347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        int[] ant = new int[k];
        for (int i = 0; i < k; i++) {
            ant[i] = queue.poll().getKey();
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new S_347().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(new S_347().topKFrequent(new int[]{1}, 1)));
    }
}
