package com.quan.algorithm.graph.trie;

import java.util.Objects;

/**
 * @author Force-oneself
 * @description Trie
 * @date 2022-04-05
 */
public class Trie {

    public TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (Objects.isNull(word)) {
            return;
        }
        char[] chars = word.toCharArray();
        TrieNode node = this.root;
        node.pass++;
        int index;
        for (char item : chars) {
            // 获取字符下标
            index = item - 'a';
            if (Objects.isNull(node.nextLArray[index])) {
                // 为空则初始化一条路径
                node.nextLArray[index] = new TrieNode();
            }
            // node转移到新的节点上
            node = node.nextLArray[index];
            node.pass++;
        }
        // 最后一位node
        node.end++;
    }

    public void delete(String word) {
        // 先查询是否存在
        if (search(word) == 0) {
            return;
        }
        char[] chars = word.toCharArray();
        TrieNode node = this.root;
        node.pass--;
        int index;
        for (char item : chars) {
            // 获取字符下标
            index = item - 'a';
            if (--node.nextLArray[index].pass == 0) {
                node.nextLArray[index] = null;
                return;
            }
            // node转移到下一个的节点上
            node = node.nextLArray[index];
        }
        node.end--;
    }

    public int search(String word) {
        if (Objects.isNull(word)) {
            return 0;
        }
        char[] chars = word.toCharArray();
        TrieNode node = this.root;
        int index;
        for (char item : chars) {
            // 获取字符下标
            index = item - 'a';
            if (Objects.isNull(node.nextLArray[index])) {
                return 0;
            }
            // node转移到下一个的节点上
            node = node.nextLArray[index];
        }
        return node.end;
    }

    public int prefixNumber(String pre) {
        if (Objects.isNull(pre)) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        TrieNode node = this.root;
        int index;
        for (char item : chars) {
            // 获取字符下标
            index = item - 'a';
            if (Objects.isNull(node.nextLArray[index])) {
                return 0;
            }
            // node转移到下一个的节点上
            node = node.nextLArray[index];
        }
        return node.pass;
    }
}
