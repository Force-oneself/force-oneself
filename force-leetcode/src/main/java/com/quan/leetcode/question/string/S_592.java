package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.List;

/**
 * S_592
 *
 * @author Force-oneself
 * @date 2022-05-30
 */
public class S_592 {

    public String fractionAddition(String expression) {
        List<Character> sign = new ArrayList<>();
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-')
                sign.add(expression.charAt(i));
        }
        List<Integer> num = new ArrayList<>();
        List<Integer> den = new ArrayList<>();
        for (String sub : expression.split("\\+")) {
            for (String subsub : sub.split("-")) {
                if (subsub.length() > 0) {
                    String[] fraction = subsub.split("/");
                    num.add(Integer.parseInt(fraction[0]));
                    den.add(Integer.parseInt(fraction[1]));
                }
            }
        }
        if (expression.charAt(0) == '-')
            num.set(0, -num.get(0));
        int lcm = 1;
        // 计算所有分母大最小公倍数
        for (int x : den) {
            lcm = lcm_(lcm, x);
        }

        int res = lcm / den.get(0) * num.get(0);
        for (int i = 1; i < num.size(); i++) {
            if (sign.get(i - 1) == '+')
                res += lcm / den.get(i) * num.get(i);
            else
                res -= lcm / den.get(i) * num.get(i);
        }
        int g = gcd(Math.abs(res), Math.abs(lcm));
        return (res / g) + "/" + (lcm / g);
    }

    /**
     * 最小公倍数
     *
     * @param a a
     * @param b b
     * @return  return
     */
    public int lcm_(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 最大公约数
     *
     * @param a a
     * @param b b
     * @return  return
     */
    public int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
