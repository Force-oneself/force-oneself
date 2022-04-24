package com.quan.leetcode.question.bs;

/**
 * S_875
 *
 * @author Force-oneself
 * @date 2022-04-21
 */
public class S_875 {

    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;
    }

    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles)
            // 取小于该数的最小整数
            time += (p - 1) / K + 1;
        return time <= H;
    }
}
