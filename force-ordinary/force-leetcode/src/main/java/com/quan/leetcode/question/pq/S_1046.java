package com.quan.leetcode.question.pq;

import java.util.PriorityQueue;

/**
 * S_1046
 *
 * @author Force-oneself
 * @date 2022-05-08
 */
public class S_1046 {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

}
