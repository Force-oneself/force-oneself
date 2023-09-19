package com.quan.leetcode.question.bs;

/**
 * S_1712
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public class S_1712 {

    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        final int MOD = 1000000000 + 7;
        long ans = 0;
        // |______|________|_______|________|
        // 1      i        l       r        n
        // i 表示第一刀的位置，枚举第一刀的位置，计算第二刀的可选位置数
        for (int i = 1, l = 2, r = 2; i <= n - 1; i++) {
            l = Math.max(l, i + 1);
            r = Math.max(r, i + 1);
            // sum(right) >= sum(mid)，r最大为n-1，right保证要有一个数
            while (r <= n - 1 && sum[n] - sum[r] >= sum[r] - sum[i]) {
                r++;
            }
            // sum(mid) >= sum(left)
            while (l <= n - 1 && sum[l] - sum[i] < sum[i]) {
                l++;
            }
            if (l <= r) {
                ans += r - l;
            }
        }
        return (int) (ans % MOD);
    }
}
