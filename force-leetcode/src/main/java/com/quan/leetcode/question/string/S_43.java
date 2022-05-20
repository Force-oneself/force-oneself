package com.quan.leetcode.question.string;

/**
 * S_43
 *
 * @author Force-oneself
 * @date 2022-05-20
 */
public class S_43 {

    /**
     * 溢出
     *
     * @param num1 num1
     * @param num2 num2
     * @return return
     */
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int n1 = 0, n2 = 0;
        for (int i = 0; i < len1; i++) {
            int n = num1.charAt(i) - '0';
            n1 = n1 * 10 + n;
        }

        for (int i = 0; i < len2; i++) {
            int n = num2.charAt(i) - '0';
            n2 = n2 * 10 + n;
        }
        return String.valueOf(n1 * n2);
    }

    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(new S_43().multiply("2", "3"));
        System.out.println(new S_43().multiply("123", "456"));
    }
}
