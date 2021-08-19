package com.zhao.leetcode;

public class TreeKLarge {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int count =0;
    int result;
    public int kthLargest(TreeNode root, int k) {
        inorder(root,k);
        return result;
    }
    private void  inorder(TreeNode root, int k){
        if (count>=k) return;
        if (root==null) return;
        inorder(root.right,k);
        count++;
        if (count==k){
            result=root.val;
            return;
        }
        inorder(root.left,k);
    }

}
