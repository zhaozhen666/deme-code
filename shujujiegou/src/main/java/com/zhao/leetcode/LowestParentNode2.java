package com.zhao.leetcode;
//68 剑指offer
public class LowestParentNode2 {
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode x= root;
        while (true){
            if (p.val<x.val&&q.val<x.val){
                x=x.left;
            }else if (p.val>x.val&&q.val>x.val){
                x=x.right;
            }else {
                break;
            }
        }
        return x;
    }
}
