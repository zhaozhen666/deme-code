package com.zhao.leetcode;

public class AddTwoNumbers {

      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 =l1;
        ListNode p2 = l2;
        ListNode head = new ListNode();
        ListNode tail = head;
        int ca = 0;
        while (p1!=null||p2!=null){
            int sum =0;
            if (p1!=null){
                sum+=p1.val;
                p1 = p1.next;
            }
            if (p2!=null){
                sum+=p2.val;
                p2 = p2.next;
            }
            if (ca!=0){
                sum +=ca;
            }
            tail.next =new ListNode(sum%10) ;
            ca = sum/10;
            tail = tail.next;


        }
        if (ca!=0){
            tail.next = new ListNode(ca);
        }
        return head.next;
    }

}
