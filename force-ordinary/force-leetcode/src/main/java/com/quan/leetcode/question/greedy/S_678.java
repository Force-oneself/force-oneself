package com.quan.leetcode.question.greedy;

/**
 * @author Force-oneself
 * @description S_678
 * @date 2022-04-10
 */
public class S_678 {

    public boolean checkValidString(String s) {
        int minCount = 0, maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 遇到左括号 最小最大值都+1
            if (c == '(') {
                minCount++;
                maxCount++;
            } else if (c == ')') {
                minCount = Math.max(minCount - 1, 0);
                maxCount--;
                // 最大值小于0说明 最大都无法匹配上右括号
                if (maxCount < 0) {
                    return false;
                }
            } else {
                // 遇到 * 可以当右括号 则需要-1，如何左括号都没有了 *作为右括号就没有意义
                minCount = Math.max(minCount - 1, 0);
                maxCount++;
            }
        }
        return minCount == 0;
    }
}
