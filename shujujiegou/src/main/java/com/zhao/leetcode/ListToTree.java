package com.zhao.leetcode;
// 105
public class ListToTree {
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    private  TreeNode buildTree(int[] preorder,int i,int j,int [] inorder,int p,int r){
        if (i>j){
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int q=p;
        while (q<=r&&inorder[q]!=preorder[i]){
            q++;
        }

        int leftTreeSize =q-p;
        TreeNode leftTreeNode = buildTree(preorder,i+1,i+leftTreeSize,inorder,p,q-1);
        TreeNode rightTreeNode = buildTree(preorder,i+leftTreeSize+1,j,inorder,q+1,r);
        root.left=leftTreeNode;
        root.right =rightTreeNode;
        return root;
    }
}
