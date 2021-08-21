package com.zhao.leetcode;

public class LeiJiaTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private int sum =0;

    public TreeNode convertBST(TreeNode root) {
        inorder(root);
        return root;
    }
    public void inorder(TreeNode root){
        if (root==null) return;
        inorder(root.right);
        sum+=root.val;
        root.val =sum;
        inorder(root.left);
    }
}
