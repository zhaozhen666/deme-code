package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeHouBianli {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorderTravel(TreeNode root){
            postorder(root);
            return result;
    }

    private void postorder(TreeNode root){
        if (root==null) return ;
        postorder(root.left);
        postorder(root.right);
        result.add(root.val);
    }
}
