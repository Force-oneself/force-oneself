package com.quan.leetcode.question.greedy;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @description S_134
 * @date 2022-04-06
 */
public class S_134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        //考虑从每一个点出发
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            //当前剩余的油能否到达下一个点
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }
        }
        //任何点都不可以
        return -1;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        //记录能到的最远距离
        int[] farIndex = new int[n];
        Arrays.fill(farIndex, -1);
        //记录到达最远距离时候剩余的汽油
        int[] farIndexRemain = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //到达下个点后的剩余
                remain = remain - cost[j];
                j = (j + 1) % n;
                //判断之前有没有考虑过这个点
                if (farIndex[j] != -1) {
                    //加上当时剩余的汽油
                    remain = remain + farIndexRemain[j];
                    //j 进行跳跃
                    j = farIndex[j];
                } else {
                    //加上当前点的补给
                    remain = remain + gas[j];
                }
                if (j == i) {
                    return i;
                }
            }
            //记录当前点最远到达哪里
            farIndex[i] = j;
            //记录当前点的剩余
            farIndexRemain[i] = remain;
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        StringBuffer stringBuffer = new StringBuffer();

        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }
            //最远距离绕到了之前，所以 i 后边的都不可能绕一圈了
            if (j < i) {
                return -1;
            }
            //i 直接跳到 j，外层 for 循环执行 i++，相当于从 j + 1 开始考虑
            i = j;

        }
        return -1;
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

}
