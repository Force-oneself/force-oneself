package com.quan.algorithm.tree;

/**
 * @author Force-oneself
 * @description Test
 * @date 2022-03-27
 */
public class Test {

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root==null) return "#";
            return root.val+ serialize(root.left)  + serialize(root.left);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if("#".equals(data)) return null;
            return tree(data, 0, data.length());
        }

        public TreeNode tree(String data, int l, int r) {
            String c = data.substring(l, l+1);
            if("#".equals(c)) return null;
            int val = Integer.parseInt(c);
            TreeNode root = new TreeNode(val);
            root.left = tree(data, l+1, (l+r+1)/2);
            root.right = tree(data, (l+r+1)/2, r);
            return root;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("1#2##");
        System.out.println(codec.serialize(node));
        System.out.println(node);
    }
}
