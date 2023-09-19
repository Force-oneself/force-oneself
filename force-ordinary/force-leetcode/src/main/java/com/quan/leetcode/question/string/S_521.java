package com.quan.leetcode.question.string;

/**
 * S_521
 *
 * @author Force-oneself
 * @date 2022-05-28
 */
public class S_521 {

    public int findLUSlength(String a, String b) {
        return !a.equals(b) ? Math.max(a.length(), b.length()) : -1;
    }

    public static void main(String[] args) {
        System.out.println(new S_521().findLUSlength("aba", "cdc"));
        System.out.println(new S_521().findLUSlength("aaa", "bbb"));
        System.out.println(new S_521().findLUSlength("aaa", "aaa"));
    }
}
