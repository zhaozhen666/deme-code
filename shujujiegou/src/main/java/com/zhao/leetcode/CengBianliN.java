package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//102
public class CengBianliN {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<List<Integer>>  levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root==null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> curNodes = new ArrayList<>();
            int curNum = queue.size();
            for (int i=0;i<curNum;++i){
                TreeNode node = queue.poll();
                curNodes.add(node.val);
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }

            }
            result.add(curNodes);
        }
        return  result;
    }
}
