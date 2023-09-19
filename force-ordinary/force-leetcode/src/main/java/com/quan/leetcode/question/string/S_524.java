package com.quan.leetcode.question.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * S_524
 *
 * @author Force-oneself
 * @date 2022-05-28
 */
public class S_524 {

    public String findLongestWord(String s, List<String> dictionary) {
        List<String> ant = new ArrayList<>();
        for (String d : dictionary) {
            if (isSub(s, d)) {
                ant.add(d);
            }
        }
        int size = ant.size();
        if (size == 0) {
            return "";
        }
        if (size == 1) {
            return ant.get(0);
        }
        ant.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int com = o2.length() - o1.length();
                return com == 0 ? o1.compareTo(o2) : com;
            }
        });
        return ant.get(0);
    }

    public boolean isSub(String f, String s) {
        int j = 0;
        for (int i = 0; i < f.length() && j < s.length(); i++) {
            if (f.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        return j == s.length();
    }
}
