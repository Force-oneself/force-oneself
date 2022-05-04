package com.quan.leetcode.question.sw;

/**
 * S_1839
 *
 * @author Force-oneself
 * @date 2022-05-04
 */
public class S_1839 {

    public int longestBeautifulSubstring(String word) {
        char[] c = word.toCharArray();
        int ans = 0;
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] == 'a') {
                int j = i + 1;
                while (j < len && c[j] >= c[j - 1]) {
                    j++;
                }
                int numOfLetters = 0;
                for (int k = i + 1; k < j; k++) {
                    if (c[k] != c[k - 1]) {
                        numOfLetters++;
                    }
                }
                if (numOfLetters == 4) {
                    ans = Math.max(ans, j - i);
                }
                i = j - 1;
            }
        }
        return ans;
    }

}
