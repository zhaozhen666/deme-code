package com.zhao.leetcode;

public class MergeTwoLinkList {

    public class ListNode {
        int val;
       ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode();
        ListNode tail = head;
        while (p1!=null&&p2!=null){
            if (p1.val>p2.val){
                tail.next = p1;
                p1=p1.next;
                tail=tail.next;
            }else {
                tail.next=p2;
                p2=p2.next;
                tail=tail.next;
            }
        }
        if (p1!=null){
            tail.next=p1;
        }
        if (p2!=null){
            tail.next=p2;
        }
        return head.next;
    }
}
