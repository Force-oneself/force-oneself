package com.quan.demo.algorithm.demo;

/**
 * ExpressionNumber
 * <p>
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。返回express能有多少种组合 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false
 * 无组合则可以得到false，返回0
 *
 * @author Force-oneself
 * @date 2022-07-21
 */
public class ExpressionNumber {

    /**
     * 校验表达式是否合法
     *
     * @param exp exp
     * @return  /
     */
    public static boolean isValid(char[] exp) {
        // 字符一定为奇数
        if ((exp.length & 1) == 0) {
            return false;
        }
        // 奇数位上一定为 0/1
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        // 偶数位上一定为 符号
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }

    public static int num1(String express, boolean desired) {
        if (express == null || "".equals(express)) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        return f(exp, desired, 0, exp.length - 1);
    }

    /**
     * str[l..r] 返回期待为desired的方法数
     * 潜台词：l r 必须是偶数位置
     *
     * @param str     str
     * @param desired desired
     * @param l       l
     * @param r       r
     * @return /
     */
    public static int f(char[] str, boolean desired, int l, int r) {
        // base case 1
        if (l == r) {
            if (str[l] == '1') {
                return desired ? 1 : 0;
            } else { // '0'
                return desired ? 0 : 1;
            }
        }

        // l..r
        int res = 0;
        // 期待为true
        if (desired) {
            // i位置尝试L..R范围上的每一个逻辑符号，都是最后结合的
            for (int i = l + 1; i < r; i += 2) {
                // exp[i] 一定压中逻辑符号
                switch (str[i]) {
                    case '&':
                        res += f(str, true, l, i - 1) * f(str, true, i + 1, r);
                        break;
                    case '|':
                        res += f(str, true, l, i - 1) * f(str, false, i + 1, r);
                        res += f(str, false, l, i - 1) * f(str, true, i + 1, r);
                        res += f(str, true, l, i - 1) * f(str, true, i + 1, r);
                        break;
                    case '^':
                        res += f(str, true, l, i - 1) * f(str, false, i + 1, r);
                        res += f(str, false, l, i - 1) * f(str, true, i + 1, r);
                        break;
                    default:
                        return 0;
                }
            }
            // 期待为false
        } else {
            for (int i = l + 1; i < r; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, false, l, i - 1) * f(str, true, i + 1, r);
                        res += f(str, true, l, i - 1) * f(str, false, i + 1, r);
                        res += f(str, false, l, i - 1) * f(str, false, i + 1, r);
                        break;
                    case '|':
                        res += f(str, false, l, i - 1) * f(str, false, i + 1, r);
                        break;
                    case '^':
                        res += f(str, true, l, i - 1) * f(str, true, i + 1, r);
                        res += f(str, false, l, i - 1) * f(str, false, i + 1, r);
                        break;
                    default:
                        return 0;
                }
            }
        }
        return res;
    }

    public static int dpLive(String express, boolean desired) {
        char[] str = express.toCharArray();
        int n = str.length;
        int[][] tMap = new int[n][n];
        int[][] fMap = new int[n][n];
        for (int i = 0; i < n; i += 2) {
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }
        for (int row = n - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < n; col += 2) {
                // row..col tMap fMap
                for (int i = row + 1; i < col; i += 2) {
                    switch (str[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        default:
                            return 0;
                    }
                    switch (str[i]) {
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        default:
                            return 0;
                    }
                }
            }
        }
        return desired ? tMap[0][n - 1] : fMap[0][n - 1];
    }

    public static int dp(String s, boolean d) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] tMap = new int[n][n];
        int[][] fMap = new int[n][n];
        for (int i = 0; i < n; i += 2) {
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1 : 0;
        }
        for (int row = n - 3; row >= 0; row = row - 2) {
            for (int col = row + 2; col < n; col = col + 2) {
                // row..col tMap fMap
                for (int i = row + 1; i < col; i += 2) {
                    switch (str[i]) {
                        case '&':
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '|':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            tMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        case '^':
                            tMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            tMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            break;
                        default:
                            return 0;
                    }
                    switch (str[i]) {
                        case '&':
                            fMap[row][col] += fMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += tMap[row][i - 1] * fMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '|':
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        case '^':
                            fMap[row][col] += tMap[row][i - 1] * tMap[i + 1][col];
                            fMap[row][col] += fMap[row][i - 1] * fMap[i + 1][col];
                            break;
                        default:
                            return 0;
                    }
                }
            }
        }
        return d ? tMap[0][n - 1] : fMap[0][n - 1];
    }

    public static int num2(String express, boolean desired) {
        if (express == null || "".equals(express)) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for (int i = 2; i < exp.length; i += 2) {
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));
        System.out.println(dp(express, desired));
        System.out.println(dpLive(express, desired));
    }
}
