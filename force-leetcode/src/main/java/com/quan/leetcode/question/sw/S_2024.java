package com.quan.leetcode.question.sw;

/**
 * S_2024
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public class S_2024 {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int len = answerKey.length();
        int r = 0;
        int l = 0;
        int t = 0;
        int f = 0;
        int ant = 0;
        while (r < len) {
            char right = answerKey.charAt(r);
            if (right == 'T') {
                t++;
            } else {
                f++;
            }
            while (t > k && f > k) {
                char left = answerKey.charAt(l);
                if (left == 'T') {
                    t--;
                } else {
                    f--;
                }
                l++;
            }
            ant = Math.max(ant, t + f);
            r++;
        }
        return ant;
    }

    /**
     * 计算连续T和F的最大值即为答案
     *
     * @param answerKey answerKey
     * @param k k
     * @return  return
     */
    public int maxConsecutiveAnswers1(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new S_2024().maxConsecutiveAnswers("TTFF", 2));
        System.out.println(new S_2024().maxConsecutiveAnswers("TFFT", 1));
        System.out.println(new S_2024().maxConsecutiveAnswers("TTFTTFTT", 1));
    }
}
