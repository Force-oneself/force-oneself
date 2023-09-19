package com.quan.leetcode.question.string;

/**
 * S_415
 *
 * @author Force-oneself
 * @date 2022-05-25
 */
public class S_415 {

    public String addStrings(String num1, String num2) {
        int len2 = num2.length();
        int len1 = num1.length();
        int i = len1 - 1, j = len2 - 1;
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = n1 + n2 + pre;
            sb.append((sum) % 10);
            pre = sum / 10;
            i--;
            j--;
        }

        if (pre > 0) {
            sb.append(pre);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new S_415().addStrings("11", "123"));
        System.out.println(new S_415().addStrings("456", "77"));
        System.out.println(new S_415().addStrings("0", "0"));
    }
}
