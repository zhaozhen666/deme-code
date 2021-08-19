package com.zhao.leetcode;

import java.util.ArrayList;
import java.util.List;

//589
public class NChaQian {
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
    private List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        preorder_r(root);
        return result;
    }

    public void preorder_r(Node root){
        if (root ==null) return;
        result.add(root.val);
        for (int i=0;i<root.children.size();i++){
            preorder_r(root.children.get(i));
        }
    }

}
