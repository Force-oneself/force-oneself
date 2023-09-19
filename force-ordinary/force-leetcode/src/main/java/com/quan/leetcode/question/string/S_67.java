package com.quan.leetcode.question.string;

/**
 * S_67
 *
 * @author Force-oneself
 * @date 2022-05-20
 */
public class S_67 {

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();

        int lena = a.length();
        int lenb = b.length();
        int n = Math.max(lena, lenb), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < lena ? (a.charAt(lena - 1 - i) - '0') : 0;
            carry += i < lenb ? (b.charAt(lenb - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_67().addBinary("11", "1"));
//        System.out.println(new S_67().addBinary("1010", "1011"));
    }
}
