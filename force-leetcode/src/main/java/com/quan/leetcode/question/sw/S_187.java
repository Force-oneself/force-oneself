package com.quan.leetcode.question.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * S_187
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public class S_187 {

    static final int L = 10;

    /**
     * hash
     *
     * @param s s
     * @return return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        Map<String, Integer> cnt = new HashMap<>();
        int n = s.length();
        for (int i = 0; i <= n - L; ++i) {
            String sub = s.substring(i, i + L);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }

    /**
     * DNA序列对应二进制标识
     */
    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    /**
     * 滑动 + 位运算
     *
     * @param s s
     * @return  return
     */
    public List<String> findRepeatedDnaSequences1(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        if (n <= L) {
            return ans;
        }
        int x = 0;
        // 先加入9个字符
        for (int i = 0; i < L - 1; ++i) {
            // 左移两位 在低位添加新的字符
            x = (x << 2) | bin.get(s.charAt(i));
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i <= n - L; ++i) {
            // 左移两位 在低位添加新的字符，i+L-1为第十个字符
            x = ((x << 2) | bin.get(s.charAt(i + L - 1)))
                    // 低20位 1111 1111 1111 1111 1111
                    // 这里会将高位置零
                    & ((1 << (L * 2)) - 1);
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            if (cnt.get(x) == 2) {
                ans.add(s.substring(i, i + L));
            }
        }
        return ans;
    }
}
