package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeQianBianli {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTravel(TreeNode root){
            preorder(root);
            return result;
    }

    private void preorder(TreeNode root){
        if (root==null) return ;
        result.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}
