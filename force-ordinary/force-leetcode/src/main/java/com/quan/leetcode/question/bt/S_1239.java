package com.quan.leetcode.question.bt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description 给定一个字符串数组 arr，字符串 s 是将 arr 的含有 不同字母 的 子序列 字符串 连接 所得的字符串。
 * <p>
 * 请返回所有可行解 s 中最长长度。
 * <p>
 * 子序列 是一种可以从另一个数组派生而来的数组，通过删除某些元素或不删除元素而不改变其余元素的顺序。
 * @date 2022-04-04
 */
public class S_1239 {

    /**
     * 迭代 + 位运算
     *
     * @param arr arr
     * @return /
     */
    public int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                // mask 用26位代表26字母
                if (((mask >> ch) & 1) != 0) {
                    // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                // 将 ch 加入 mask 中
                mask |= 1 << ch;
            }
            if (mask == 0) {
                continue;
            }
            // 在存在的marks中拼接新的字符
            int n = masks.size();
            for (int i = 0; i < n; ++i) {
                int m = masks.get(i);
                // m 和 mask 无公共元素
                if ((m & mask) == 0) {
                    masks.add(m | mask);
                    ans = Math.max(ans, Integer.bitCount(m | mask));
                }
            }
        }
        return ans;
    }

    int ans = 0;

    /**
     * 回溯 + 位运算
     *
     * @param arr arr
     * @return /
     */
    public int maxLength2(List<String> arr) {
        List<Integer> masks = new ArrayList<Integer>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) { // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                mask |= 1 << ch; // 将 ch 加入 mask 中
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }

        backtrack(masks, 0, 0);
        return ans;
    }

    public void backtrack(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        if ((mask & masks.get(pos)) == 0) { // mask 和 masks[pos] 无公共元素
            backtrack(masks, pos + 1, mask | masks.get(pos));
        }
        backtrack(masks, pos + 1, mask);
    }

}
