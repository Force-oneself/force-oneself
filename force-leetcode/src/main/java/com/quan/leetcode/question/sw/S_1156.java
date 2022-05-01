package com.quan.leetcode.question.sw;

/**
 * S_1156
 *
 * @author Force-oneself
 * @date 2022-05-01
 */
public class S_1156 {

    /**
     * 错误答案
     *
     * @param text text
     * @return return
     */
    public int maxRepOpt11(String text) {
        int[] last = new int[26];
        int len = text.length();
        // 每个元素最后出现的位置
        for (int i = 0; i < len; i++) {
            last[text.charAt(i) - 'a'] = i;
        }

        int l = 0;
        int r = 0;
        int max = 0;
        boolean change = false;
        while (r < len) {
            while (text.charAt(l) != text.charAt(r)) {
                // 最后出现该元素的位置需要在r的后面并且只被交换一次
                if (last[text.charAt(l) - 'a'] > r && !change) {
                    change = true;
                    break;
                }
                // 交换过还是不同则右移到r，前面相同到字符[l, r-1]
                // 这段相同到已经和后面字符无法继续过程相同字符数组了
                l = r;
            }
            // 其中有元素改变过，当r元素到被换过的元素位置时需要减掉当前的元素计数
            if (change && last[text.charAt(l) - 'a'] == r) {
                // 当前元素已经是被替换过的 所以需要-1，并将l右移到r的位置重新开始
                max = Math.max(max, r - l);
                l = r;
                change = false;
            } else {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }
        return max;
    }

    /**
     * 滑动窗口
     *
     * @param text text
     * @return return
     */
    public int maxRepOpt1(String text) {
        char[] cs = text.toCharArray();
        // 每个字符计数
        int[] dics = new int[26];
        for (char c : cs) {
            dics[c - 'a'] += 1;
        }
        int len = cs.length;
        // 窗口内，第一个字符和数量
        char A = cs[0], last = A;
        int a = 1;
        int l = 0, r = 1;
        while (r < len && cs[r] == last) {
            r++;
            a++;
        }
        // 有可能就一个啊
        if (r == len || r == len - 1) {
            return r;
        }
        // 窗口里第二个字符
        char B = cs[r];
        last = B;
        int b = 1;
        r++;
        int ans = 0;
        while (r <= len) {
            char cur = r < len ? cs[r] : ' ';
            if (cur == A && a >= 1 && b <= 1) {
                // 窗口中不唯一的字符是A字符，A字符+1；
                a++;
                r++;
            } else if (cur == B && b >= 1 && a <= 1) {
                // 窗口中不唯一的字符是B字符，B字符+1；
                b++;
                r++;
            } else {
                // 窗口结构破坏，现有窗口内数字结算
                if (a == 1) {
                    // A是独苗，结算B
                    if (dics[B - 'a'] > b) {
                        ans = Math.max(ans, b + 1);
                    } else {
                        ans = Math.max(ans, b);
                    }
                } else {
                    // B是独苗，结算A
                    if (dics[A - 'a'] > a) {
                        ans = Math.max(ans, a + 1);
                    } else {
                        ans = Math.max(ans, a);
                    }
                }
                if (cur != A && cur != B) {
                    // cur不是A，B中一个。将a，b其中一个清空
                    while (a > 0 && b > 0) {
                        char c = cs[l++];
                        if (c == A) {
                            a--;
                        } else {
                            b--;
                        }
                    }
                    // 让A拿着不为空的字符信息
                    if (a == 0) {
                        A = B;
                        a = b;
                    }
                    // 找到B的字符信息。
                    while (r < len && cs[r] == A) {
                        a++;
                        r++;
                    }
                    if (r < len) {
                        B = cs[r];
                        b = 1;
                    }
                } else {
                    // 如果cur是A,B中一个，先加入进来
                    if (cur == A) {
                        a++;
                    } else {
                        b++;
                    }
                    // 将两个字符，变成不会同时存在两个字符都有两个以上的情况。至少有1个变成1
                    while (a > 1 && b > 1) {
                        char c = cs[l++];
                        if (c == A) {
                            a--;
                        } else {
                            b--;
                        }
                    }
                }
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new S_1156().maxRepOpt1("ababa"));
//        System.out.println(new S_1156().maxRepOpt1("aaabaaa"));
//        System.out.println(new S_1156().maxRepOpt1("aaabbaaa"));
//        System.out.println(new S_1156().maxRepOpt1("aaaaa"));
//        System.out.println(new S_1156().maxRepOpt1("abcdef"));
        System.out.println(new S_1156().maxRepOpt1("bbababaaaa"));
//        System.out.println(new S_1156().maxRepOpt1("aaaaabaaaaaaaabbaaaaaaaaaaabba"));
    }
}
