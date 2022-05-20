package com.quan.leetcode.question.string;

/**
 * S_28
 *
 * @author Force-oneself
 * @date 2022-05-19
 */
public class S_28 {

    public int strStr(String haystack, String needle) {
        char[] haystackCharArr = haystack.toCharArray();
        char[] needleCharArr = needle.toCharArray();
        int haystackLen = haystackCharArr.length;
        int needleLen = needleCharArr.length;
        if (haystackLen < needleLen) {
            return -1;
        }

        for (int i = 0; i <= haystackLen - needleLen; i++) {
            boolean match = true;
            for (int j = 0; j < needleLen; j++) {
                if (needleCharArr[j] != haystackCharArr[i+j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(new S_28().strStr("a", "a"));
    }

}
