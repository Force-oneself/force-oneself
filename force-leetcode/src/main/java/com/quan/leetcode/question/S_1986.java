package com.quan.leetcode.question;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_1986
 * @date 2022-04-13
 */
public class S_1986 {

    int ans = 0;
    public int minSessions1(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);
        bt(tasks, sessionTime, new boolean[tasks.length]);
        return ans;
    }

    public void bt(int[] tasks, int sessionTime, boolean[] use) {
        if (sessionTime < 0) return;
        for (int i = 0; i < tasks.length; i++) {
            if (use[i] || tasks[i] > sessionTime) continue;
            use[i] = true;
            bt(tasks, sessionTime - tasks[i], use);
            use[i] = false;
        }
        ans++;
    }

    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int k = 1 << n;
        boolean[] vaild = new boolean[k];
        for (int mask = 1; mask < k; mask++) {
            int needTime = 0;
            for (int i = 0; i < n; i++) {
                // 第i位上第任务与mask任务相同
                if ((mask & (1 << i)) != 0) {
                    // 计算该枚举的所需时间
                    needTime += tasks[i];
                }
            }
            if (needTime <= sessionTime) {
                vaild[mask] = true;
            }
        }
        int[] dp = new int[k];
        for (int mask = 1; mask < k; mask++) {
            dp[mask] = Integer.MAX_VALUE;
            for (int subset = mask; subset != 0; subset = (subset - 1) & mask) {
                if (vaild[subset]) dp[mask] = Math.min(dp[mask], dp[mask ^ subset] + 1);
            }
        }
        return dp[k - 1];
    }
}
