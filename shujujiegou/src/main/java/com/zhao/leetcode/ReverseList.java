package com.zhao.leetcode;

public class ReverseList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList(ListNode head){
        if (head==null) return null;
        if (head.next==null) return null;
        ListNode newHead =reverseList(head.next);
        head.next.next=head;
        head.next =null;
        return newHead;
    }
}
