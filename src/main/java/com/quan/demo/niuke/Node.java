package com.quan.demo.niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Node {

    public  TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,5,8,6,5,8,2,8,8};
        int max = 0;
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                temp++;
            } else {
                temp = 1;
            }
            if (temp > max) {
                max = temp;
            }
        }
        System.out.println(Math.round(-15.61));


    }

    class LRU {
        private Map<Integer, Integer> cache = new HashMap<>();
        private List<Integer> logs = new LinkedList<>();
        private Integer use;
        private Integer noUse;
        private Integer k;

        public Integer get(Integer key) {
            use = key;
            log(key);
            return cache.get(key);
        }

        public void set(Integer key, Integer value) {
            use = key;
            log(key);
            if (threshold()) {
                remove();
            }
            if (cache.containsKey(key)) {
                matchIfRemove(key);
            }
            cache.put(key, value);
        }

        /**
         * 删除缓存
         */
        public void remove() {
            cache.remove(noUse);
        }

        /**
         * 添加node进入log列表,并删除重复node更新列表
         * @param node
         */
        public void log(Integer node){
            matchIfRemove(node);
            findNoUseLog();
            logs.add(node);
        }

        private void matchIfRemove(Integer node) {
            int nodeIndex = logs.indexOf(node);
            if (nodeIndex > -1) {
                logs.remove(nodeIndex);
            }
        }

        /**
         * 返回第一个即为最不常用key
         */
        public void findNoUseLog() {
            noUse = logs.get(0);
        }

        public Boolean threshold() {
            return cache.size() > k;
        }

        public Integer getUse() {
            return use;
        }

        public void setUse(Integer use) {
            this.use = use;
        }

        public Integer getNoUse() {
            return noUse;
        }

        public void setNoUse(Integer noUse) {
            this.noUse = noUse;
        }

        public Integer getK() {
            return k;
        }

        public void setK(Integer k) {
            this.k = k;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
