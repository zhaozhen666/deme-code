package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//589
public class NChaCeng {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<List<Integer>>  levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root==null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> curNodes = new ArrayList<>();
            int curNum = queue.size();
            for (int i=0;i<curNum;++i){
                Node node = queue.poll();
                curNodes.add(node.val);
                for (int j=0;j<node.children.size();++j){
                    queue.add(node.children.get(i));
                }

            }
            result.add(curNodes);
        }
        return  result;
    }

}
