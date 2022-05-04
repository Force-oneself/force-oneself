package com.quan.leetcode.question.sw;

/**
 * S_1763
 *
 * @author Force-oneself
 * @date 2022-05-04
 */
public class S_1763 {

    /**
     * 枚举
     *
     * @param s s
     * @return return
     */
    public String longestNiceSubstring1(String s) {
        int n = s.length();
        int maxPos = 0;
        int maxLen = 0;
        for (int i = 0; i < n; ++i) {
            int lower = 0;
            int upper = 0;
            for (int j = i; j < n; ++j) {
                if (Character.isLowerCase(s.charAt(j))) {
                    lower |= 1 << (s.charAt(j) - 'a');
                } else {
                    upper |= 1 << (s.charAt(j) - 'A');
                }
                if (lower == upper && j - i + 1 > maxLen) {
                    maxPos = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    private int maxPos;
    private int maxLen;

    public String longestNiceSubstring2(String s) {
        this.maxPos = 0;
        this.maxLen = 0;

        int types = 0;
        for (int i = 0; i < s.length(); ++i) {
            types |= 1 << (Character.toLowerCase(s.charAt(i)) - 'a');
        }
        types = Integer.bitCount(types);
        for (int i = 1; i <= types; ++i) {
            check(s, i);
        }
        return s.substring(maxPos, maxPos + maxLen);
    }

    public void check(String s, int typeNum) {
        int[] lowerCnt = new int[26];
        int[] upperCnt = new int[26];
        int cnt = 0;
        for (int l = 0, r = 0, total = 0; r < s.length(); ++r) {
            int idx = Character.toLowerCase(s.charAt(r)) - 'a';
            if (Character.isLowerCase(s.charAt(r))) {
                ++lowerCnt[idx];
                if (lowerCnt[idx] == 1 && upperCnt[idx] > 0) {
                    ++cnt;
                }
            } else {
                ++upperCnt[idx];
                if (upperCnt[idx] == 1 && lowerCnt[idx] > 0) {
                    ++cnt;
                }
            }
            total += (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
            while (total > typeNum) {
                idx = Character.toLowerCase(s.charAt(l)) - 'a';
                total -= (lowerCnt[idx] + upperCnt[idx]) == 1 ? 1 : 0;
                if (Character.isLowerCase(s.charAt(l))) {
                    --lowerCnt[idx];
                    if (lowerCnt[idx] == 0 && upperCnt[idx] > 0) {
                        --cnt;
                    }
                } else {
                    --upperCnt[idx];
                    if (upperCnt[idx] == 0 && lowerCnt[idx] > 0) {
                        --cnt;
                    }
                }
                ++l;
            }
            if (cnt == typeNum && r - l + 1 > maxLen) {
                maxPos = l;
                maxLen = r - l + 1;
            }
        }
    }

}
