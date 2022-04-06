package com.quan.algorithm.graph.tree;

/**
 * @author Force-oneself
 * @description TreeDemo
 * @date 2022-03-14
 */
public class TreeDemo {

    
    public static boolean isBalanced(TreeNode head) {
        return process(head).isBalanced;
    }


    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
                && Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }

    public static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        boolean validSerialization = isValidSerialization("9,#,#,1");
    }

    public static boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        if(arr.length == 1) return "#".equals(arr[0]);
        int j = 2;
        for(int i=1;i< arr.length; i++) {
            if(!"#".equals(arr[i])) j++;
            else j--;
            if(j==0 && i+1 < arr.length) return false;
        }
        return j == 0;
    }

}
