package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CengBianli {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public int[] levelOrder(TreeNode root) {
        if (root==null) return new int[0];
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
        int [] resultArr = new int[result.size()];
        for (int i=0;i<result.size();i++){
            resultArr[i] =result.get(i);
        }
        return  resultArr;
    }
}
