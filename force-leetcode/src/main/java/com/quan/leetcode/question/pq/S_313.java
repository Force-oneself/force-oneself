package com.quan.leetcode.question.pq;

import java.util.PriorityQueue;

/**
 * S_313
 *
 * @author Force-oneself
 * @date 2022-05-06
 */
public class S_313 {

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        while (n-- > 0) {
            int x = q.poll();
            if (n == 0) return x;
            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x) q.add(k * x);
                if (x % k == 0) break;
            }
        }
        // never
        return -1;
    }
}
