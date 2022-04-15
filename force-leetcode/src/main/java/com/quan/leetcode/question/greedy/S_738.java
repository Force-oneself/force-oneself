package com.quan.leetcode.question.greedy;

/**
 * @author Force-oneself
 * @description S_738
 * @date 2022-04-10
 */
public class S_738 {

    public int monotoneIncreasingDigits1(int n) {
        while (n >= 0) {
            char[] chars = String.valueOf(n).toCharArray();
            boolean gt = false;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i - 1] > chars[i]) {
                    gt = true;
                    break;
                }
            }
            if (!gt) {
                return n;
            }
            n--;
        }
        return n;
    }

    public int monotoneIncreasingDigits(int n) {
        char[] strN = Integer.toString(n).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            // 从最开始大于该数的值-- ， i-1 减小后可能i-2的值比他大所以需要继续往前检查并--
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            // 前面值都满足递增关系，只需要把最前面--的数后面的值变成9就完成了
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }


    public static void main(String[] args) {
        System.out.println(new S_738().monotoneIncreasingDigits(10));
        System.out.println(new S_738().monotoneIncreasingDigits(1234));
        System.out.println(new S_738().monotoneIncreasingDigits(777616726));
    }
}
