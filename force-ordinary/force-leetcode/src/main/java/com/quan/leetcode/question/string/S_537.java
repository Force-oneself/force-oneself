package com.quan.leetcode.question.string;

/**
 * S_537
 *
 * @author Force-oneself
 * @date 2022-05-29
 */
public class S_537 {

    public String complexNumberMultiply(String num1, String num2) {
        num1 = num1.replace("i", "");
        num2 = num2.replace("i", "");
        String[] split1 = num1.split("\\+");
        String[] split2 = num2.split("\\+");
        int[] n1 = new int[]{Integer.parseInt(split1[0]), Integer.parseInt(split1[1])};
        int[] n2 = new int[]{Integer.parseInt(split2[0]), Integer.parseInt(split2[1])};
        return (n1[0] * n2[0] - n1[1] * n2[1])  + "+" + (n1[0] * n2[1] + n1[1] * n2[0]) + "i";
    }

    public static void main(String[] args) {
        System.out.println(new S_537().complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(new S_537().complexNumberMultiply("1+-1i", "1+-1i"));
    }
}
