package com.quan.leetcode.question.sw;

/**
 * S_1358
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
public class S_1358 {


    public int numberOfSubstrings1(String s) {
        int[] letter = new int[3];
        int type = 0;
        int r = 0;
        int l = 0;
        int len = s.length();
        int ant = 0;
        int size = 0;
        while (r < len) {
            int rIndex = s.charAt(r) - 'a';
            letter[rIndex]++;
            size++;
            // 新的字符增加
            if (letter[rIndex] == 1) {
                type++;
            }
            while (size > 3 && type == 3) {
                int lIndex = s.charAt(l) - 'a';
                letter[lIndex]--;
                // 一种字符没了
                if (letter[lIndex] == 0) {
                    type--;
                }
                size--;
                l++;
            }
            if (type == 3) {
                // 有三种类型了说明从r到len-1到字符串都满足条件
                ant += len - r;
            }
            r++;
        }
        return ant;
    }

    /**
     * 双指针
     *
     * @param s s
     * @return return
     */
    int numberOfSubstrings2(String s) {
        int[] cnt = new int[3];
        int n = s.length();
        int left = -1, right = 0;
        int ans = 0;
        while (right < n) {
            cnt[s.charAt(right) - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                ans += n - right;
                left++;
                cnt[s.charAt(left) - 'a']--;
            }
            right++;
        }
        return ans;
    }

    public int numberOfSubstrings(String s) {
        return getSubArr(s, 3) - getSubArr(s, 2);
    }

    private int getSubArr(String s, int k) {
        int n = s.length();
        int[] cnt = new int[3];
        int m = 0;
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < n) {
            int indexR = s.charAt(right) - 'a';
            cnt[indexR]++;
            if (cnt[indexR] == 1) {
                m++;
            }
            while (m > k) {
                int indexL = s.charAt(left) - 'a';
                cnt[indexL]--;
                if (cnt[indexL] == 0) {
                    m--;
                }
                left++;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new S_1358().numberOfSubstrings("abcabc"));
        System.out.println(new S_1358().numberOfSubstrings("aaacb"));
        System.out.println(new S_1358().numberOfSubstrings("abc"));
    }
}
