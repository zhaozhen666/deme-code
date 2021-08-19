package com.zhao.leetcode;
//55 ||
public class PhTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private boolean isBalanced =true;
    public boolean isBalanced(TreeNode root) {
        height(root);
        return isBalanced;

    }

    private int height(TreeNode root){
        if (root==null){
            return 0;
        }
        if (isBalanced==false) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (Math.abs(leftHeight-rightHeight)>1){
            isBalanced=false;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }
}
