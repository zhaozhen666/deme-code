package com.zhao.leetcode;
//17.12
public class TreeToLinkList2 {
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

    private TreeNode dummyHead =new TreeNode();
    private TreeNode tail = dummyHead;
    public TreeNode convertBiNode(TreeNode root) {
        inorder(root);
        return dummyHead.right;
    }

    private void  inorder(TreeNode root){
        if (root==null) return;
        TreeNode left =root.left;
        TreeNode right = root.right;
        inorder(left);
        tail.right =root;
        tail=root;
        root.left =null;
        inorder(right);

    }
}
