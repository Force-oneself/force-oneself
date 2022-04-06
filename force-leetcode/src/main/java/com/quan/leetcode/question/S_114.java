package com.quan.leetcode.question;

import com.quan.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description Solution114
 * @date 2022-03-15
 */
public class S_114 {

    public void flatten(TreeNode root) {

        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        List<TreeNode> res = new ArrayList<>();

        order(root, res);
        if (res.isEmpty()) {
            return;
        }
        TreeNode cur = res.get(0);
        for (int i = 1; i < res.size(); i++) {
            cur.right = res.get(i);
            cur.left = null;
            cur = res.get(i);
        }

    }

    public void order(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        res.add(root);
        order(root.left, res);
        order(root.right, res);
    }

}
