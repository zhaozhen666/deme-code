package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeMiddleBianli {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    List<Integer> result = new ArrayList<>();
    public List<Integer> inorderTravel(TreeNode root){
        inorder(root);
            return result;
    }

    private void inorder(TreeNode root){
        if (root==null) return ;
        inorder(root.left);
        result.add(root.val);
        inorder(root.right);
    }
}
