package com.quan.leetcode.question.sw;

/**
 * S_1052
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_1052 {


    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length, sum = 0;
        // 在minutes窗口的和
        for (int i = 0; i < minutes; i++) {
            sum += customers[i] * grumpy[i];
        }

        int res = sum;
        // 求在minutes的窗口内可以挽回最大满意客户数
        for (int i = minutes; i < n; i++) {
            sum += customers[i] * grumpy[i] - customers[i - minutes] * grumpy[i - minutes];
            res = Math.max(res, sum);
        }
        // 原来不改变下的满意客户数
        for (int i = 0; i < n; i++) {
            res += customers[i] * (grumpy[i] ^ 1);
        }
        return res;
    }

}
