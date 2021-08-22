package com.zhao.leetcode;
//
public class ZHListToTree {
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(postorder,0,postorder.length-1,inorder,0,inorder.length-1);
    }
    private  TreeNode buildTree(int[] postorder,int i,int j,int [] inorder,int p,int r){
        if (i>j){
            return null;
        }
        TreeNode root = new TreeNode(postorder[j]);
        int q=p;
        while (q<=r&&inorder[q]!=postorder[j]){
            q++;
        }

        int leftTreeSize =q-p;
        TreeNode leftTreeNode = buildTree(postorder,i,i+leftTreeSize-1,inorder,p,q-1);
        TreeNode rightTreeNode = buildTree(postorder,i+leftTreeSize+1,j-1,inorder,q+1,r);
        root.left=leftTreeNode;
        root.right =rightTreeNode;
        return root;
    }
}
