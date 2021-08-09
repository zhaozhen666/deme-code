package com.zhao.leetcode;

public class Huiwen {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public boolean isPalindrome(ListNode head) {

        ListNode middleNode = getMiddleNode(head);
        ListNode rightHalfHead = reverseList(middleNode);
        ListNode p =head;
        ListNode q =rightHalfHead;
        while (q!=null){
            if (q.val!=p.val) return false;
            q= q.next;
            p =p.next;
        }
        return true;
    }

    ListNode  getMiddleNode(ListNode head){
        ListNode slow = head;
        ListNode fast =head;
        while (fast!=null&&fast.next!=null){
            slow =slow.next;
            fast =fast.next.next;
        }
        return slow;
    }

    ListNode reverseList(ListNode head){
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
