package com.quan.algorithm.graph.trie;

/**
 * @author Force-oneself
 * @description TrieNode
 * @date 2022-04-05
 */
public class TrieNode {
    public int pass;
    public int end;

    public TrieNode[] nextLArray;

    public TrieNode() {
        pass = 0;
        end = 0;
        nextLArray = new TrieNode[26];
    }
}
