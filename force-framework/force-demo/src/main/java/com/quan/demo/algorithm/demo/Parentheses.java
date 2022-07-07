package com.quan.demo.algorithm.demo;

/**
 * Parentheses
 *
 * @author Force-oneself
 * @date 2022-07-04
 */
public class Parentheses {

    public static boolean isValid(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int status = 0;
        for (char c : str) {
            if (c != ')' && c != '(') {
                return false;
            }
            if (c == ')' && --status < 0) {
                return false;
            }
            if (c == '(') {
                status++;
            }
        }
        return status == 0;
    }

    /**
     * 括号的最大深度 "(((())))()()" ==> 4
     *
     * @param s s
     * @return /
     */
    public static int deep(String s) {
        char[] str = s.toCharArray();
        // 不是有效的括号
        if (!isValid(str)) {
            return 0;
        }
        int count = 0;
        int max = 0;
        for (char c : str) {
            if (c == '(') {
                max = Math.max(max, ++count);
            } else {
                count--;
            }
        }
        return max;
    }

    /**
     * 有效括号最大长度
     *
     * @param s s
     * @return /
     */
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        int pre;
        int ans = 0;
        // dp[0] = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                // 与str[i]配对的左括号的位置 pre，dp[i-1] 代表前面成对的数量
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String test = "((()))";
        System.out.println(deep(test));

    }
}
