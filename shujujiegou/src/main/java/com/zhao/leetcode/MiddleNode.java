package com.zhao.leetcode;

public class MiddleNode {

      public class ListNode {
         int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode middleNode(ListNode head) {
                ListNode dummyHead = new ListNode(-1);
                dummyHead.next=head;
                ListNode slow = dummyHead;
                ListNode fast = dummyHead;
                while(fast.next!=null&&fast.next.next!=null){
                    slow =slow.next;
                    fast = fast.next.next;
                }
                return slow;
    }

}
