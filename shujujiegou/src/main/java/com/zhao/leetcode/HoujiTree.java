package com.zhao.leetcode;
//0406
public class HoujiTree {
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
    private boolean comming =false;
    private TreeNode successor =null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root,p);
        return successor;
    }

    public void inorder(TreeNode root,TreeNode p){
        if (root==null) return;

        inorder(root.left,p);
        if (successor!=null) return;
        if (comming==true){
            successor =root;
            comming =false;
            return;
        }
        if (root==p) comming =true;
        inorder(root.right,p);
    }
}
