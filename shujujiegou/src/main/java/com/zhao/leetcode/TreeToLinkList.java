package com.zhao.leetcode;
//114
public class TreeToLinkList {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private TreeNode dummyHead = new TreeNode();
    private TreeNode tail =dummyHead;
    public void flatten(TreeNode root) {
        preorder(root);
    }

    private void  preorder(TreeNode root){
        if (root==null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        tail.right =root;
        tail=root;
        root.left=null;
        preorder(left);
        preorder(right);


    }
}
