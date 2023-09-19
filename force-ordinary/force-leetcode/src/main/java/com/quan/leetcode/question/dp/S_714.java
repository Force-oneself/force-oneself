package com.quan.leetcode.question.dp;

/**
 * @author Force-oneself
 * @description S_714
 * @date 2022-04-10
 */
public class S_714 {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            // 此时股票的花费更小
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
                // 当前股票价格高于卖出的花销
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                // 如果下次股票还是涨则
                buy = prices[i];
            }
        }
        return profit;
    }

}
