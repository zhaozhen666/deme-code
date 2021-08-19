package com.zhao.leetcode;
//101
public class DuichenTree {

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

    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return isSymmetric(root.left,root.right);
    }

    private boolean isSymmetric(TreeNode left,TreeNode right){
            if (left==null&&right==null) return true;
            if (left!=null&&right!=null&&left.val==right.val){
               return isSymmetric(left.left,right.right)&&isSymmetric(left.right,right.left);
            }
            return false;
    }
}
