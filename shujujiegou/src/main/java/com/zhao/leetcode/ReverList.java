package com.zhao.leetcode;

public class ReverList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList(ListNode head) {

        ListNode newHead = null;
        ListNode p =head;
        while (p!=null){
            ListNode tmp = p.next;
            p.next =newHead;
            newHead = p;
            p =tmp;
        }
        return newHead;
    }
}
