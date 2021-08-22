package com.zhao.leetcode;
//
public class TreeToDoubleLinkList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };


    private Node dummyHead =new Node();
    private Node tail = dummyHead;
    public Node treeToDoublyList(Node root) {
        if (root==null) return null;
        inorder(root);
        tail.right=dummyHead.right;
        dummyHead.right.left =tail;
        return dummyHead.right;
    }

    private void  inorder(Node root){
        if (root==null) return;
        Node left =root.left;
        Node right = root.right;
        inorder(left);
        root.left = tail;
        tail.right =root;
        tail=root;
        inorder(right);

    }
}
