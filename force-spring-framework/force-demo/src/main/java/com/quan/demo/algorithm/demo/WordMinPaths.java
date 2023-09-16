package com.quan.demo.algorithm.demo;

import java.util.*;

/**
 * WordMinPaths
 * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串，所有的字符串都是小写的。
 * 规定: start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list 中存在。
 * 请返回所有最短的变换路径。
 * 【举例】
 * start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
 * 转换路径的方法有很多种，但所有最短的转换路径如下:
 * abc -> abb -> aab -> cab
 * abc -> abb -> cbb -> cab
 * abc -> cbc -> cac -> cab
 * abc -> cbc -> cbb -> cab
 *
 * @author Force-oneself
 * @date 2022-07-18
 */
public class WordMinPaths {

    public static List<List<String>> findMinPaths(
            String start,
            String end,
            List<String> list) {
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        HashMap<String, Integer> distances = getDistances(start, nexts);

        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start, end, nexts, distances, pathList, res);
        return res;
    }

    public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        // List 所有东西放入 set
        Set<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (String word : words) {
            nexts.put(word, getNext(word, dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }

    public static HashMap<String, Integer> getDistances(String start,
                                                        HashMap<String, ArrayList<String>> nexts) {
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    distances.put(next, distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    /**
     * 现在来到了什么：cur
     * 目的地：end
     * 邻居表：nexts
     * 最短距离表：distances
     * 沿途走过的路径：path上{....}
     * 答案往res里放，收集所有的最短路径
     *
     * @param cur       cur
     * @param to        to
     * @param nexts     nexts
     * @param distances distances
     * @param path      path
     * @param res       res
     */
    private static void getShortestPaths(
            String cur, String to,
            HashMap<String, ArrayList<String>> nexts,
            HashMap<String, Integer> distances,
            LinkedList<String> path,
            List<List<String>> res) {
        path.add(cur);
        if (to.equals(cur)) {
            res.add(new LinkedList<>(path));
        } else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1) {
                    getShortestPaths(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();
    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = {"abc", "cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb"};
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, test);
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }
}
