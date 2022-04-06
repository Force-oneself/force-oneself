package com.quan.algorithm.graph.tree;

import java.util.List;

/**
 * @author Force-oneself
 * @description TreeTemplate
 * @date 2022-03-20
 */
public class TreeTemplate {

    List<List<Integer>> res;

    /**
     * 一般路径
     *
     * @param root root
     * @param path path
     */
    void dfs(TreeNode root, List<Integer> path) {
        // 根节点为空直接返回
        if (root == null) return;
        // 作出选择
//        path.push_back(root->val);
        // 如果到叶节点
        if (root.left == null && root.right == null) {
//            res.push_back(path);
            return;
        }
        // 继续递归
        dfs(root.left, path);
        dfs(root.right, path);
    }


    /**
     * 给定和的路径
     *
     * @param root root
     * @param path path
     */
    void dfs(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }

        sum -= root.val;
//        path.push_back(root.val);

        if (root.left != null && root.right != null && sum == 0) {
//            res.push_back(path);
            return;
        }
        dfs(root.left, sum, path);
        dfs(root.right, sum, path);
    }

}
