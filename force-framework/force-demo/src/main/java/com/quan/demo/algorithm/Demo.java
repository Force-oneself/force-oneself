package com.quan.demo.algorithm;

import java.util.*;

/**
 * @author Force-oneself
 * @description Demo
 * @date 2022-03-30
 */
public class Demo {

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        boolean[] use = new boolean[nums.length];
        bt(nums, 0, use, res, set, new ArrayList<>());
        return res;
    }

    public void bt(int[] nums, int begin, boolean[] use, List<List<Integer>> res, Set<List<Integer>> set, List<Integer> ant) {
        if (ant.size() > 1) {
            ArrayList<Integer> row = new ArrayList<>(ant);
//            if (set.add(row)) res.add(row);
            res.add(row);
        }
        for (int i = begin; i < nums.length; i++) {
            if (!ant.isEmpty() && nums[i] < ant.get(ant.size() - 1)) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !use[i - 1]) continue;
            if (ant.isEmpty() && isValid(nums, i)) continue;
            ant.add(nums[i]);
            use[i] = true;
            bt(nums, i + 1, use, res, set, ant);
            ant.remove(ant.size() - 1);
            use[i] = false;
        }
    }

    private boolean isValid(int[] nums, int i) {
        for (int j = i - 1; j >= 0; j--) {
            if (nums[i] == nums[j]) {
                return true;
            }
        }
        return false;
    }




    public static void main(String[] args) {
//        new Demo().partition("aabbaa").forEach(System.out::println);

        new Demo().findSubsequences(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1}).forEach(System.out::println);
    }

}
