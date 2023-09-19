package com.quan.leetcode.question.sw;

import java.util.Arrays;

/**
 * S_1423
 *
 * @author Force-oneself
 * @date 2022-05-03
 */
public class S_1423 {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    public static void main(String[] args) {
        System.out.println(new S_1423().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(new S_1423().maxScore(new int[]{2, 2, 2}, 2));
        System.out.println(new S_1423().maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        System.out.println(new S_1423().maxScore(new int[]{1, 1000, 1}, 1));
        System.out.println(new S_1423().maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }
}
