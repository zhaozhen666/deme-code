package com.zhao.leetcode;
//617
public class MergeTree {

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 ==null&&root2 ==null) return null;

        TreeNode newNode = new TreeNode(0);
        if (root1!=null) newNode.val+=root1.val;
        if (root2!=null) newNode.val+= root2.val;

        //合并左子树
        TreeNode lt1 = null;
        if (root1!=null) lt1=root1.left;
        TreeNode lt2 = null;
        if (root2!=null) lt2 =root2.left;
        TreeNode leftRoot = mergeTrees(lt1,lt2);
        //合并右子树
        TreeNode rt1 = null;
        if (root1!=null) rt1=root1;
        TreeNode rt2 = null;
        if (root2!=null) rt2 =root2;
        TreeNode rightRoot = mergeTrees(rt1,rt2);

        newNode.left =leftRoot;
        newNode.right =rightRoot;
        return newNode;

    }


}
