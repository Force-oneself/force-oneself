package com.quan.leetcode.question.bs;

import java.util.Arrays;

/**
 * S_1552
 *
 * @author Force-oneself
 * @date 2022-04-26
 */
public class S_1552 {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int len = position.length;
        int l = 1;
        int r = position[len - 1] - position[0];
        int ans = -1;
        while (l <= r) {
            int mid = (r + l) / 2;
            boolean match = match(mid, position, m);
            if (match) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    private boolean match(int mid, int[] position, int m) {
        int pre = position[0], cnt = 1;
        for (int i = 1; i < position.length; ++i) {
            if (position[i] - pre >= mid) {
                pre = position[i];
                cnt += 1;
            }
        }
        return cnt >= m;
    }

}
