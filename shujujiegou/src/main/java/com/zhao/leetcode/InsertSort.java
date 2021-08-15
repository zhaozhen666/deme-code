package com.zhao.leetcode;

public class InsertSort {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode insertionSort(ListNode head){
        if (head==null){
            return null;
        }
        ListNode dummyHead =new ListNode(Integer.MIN_VALUE,null);
        ListNode p =head;
        while (p!=null){
            ListNode tmp = p.next;
            ListNode q = dummyHead;
            while (q.next!=null&& q.next.val<=p.val){
                q=q.next;
            }
            p.next = q.next;
            q.next= p;
            p =tmp;
        }
        return dummyHead.next;
    }
}
