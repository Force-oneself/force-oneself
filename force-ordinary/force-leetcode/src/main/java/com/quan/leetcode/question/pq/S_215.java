package com.quan.leetcode.question.pq;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * S_215
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int num : nums) {
            queue.add(num);
        }
        int ant = 0;
        for (int i = 0; i < k; i++) {
            ant = queue.poll();
        }
        return ant;
    }

    public static void main(String[] args) {
        System.out.println(new S_215().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(new S_215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
