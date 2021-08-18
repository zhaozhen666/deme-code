package com.zhao.leetcode;

import java.util.HashSet;

//02.01
public class RemoveDump {


      public class ListNode {
          int val;
         ListNode next;
          ListNode(int x) { val = x; }
      }

    public ListNode removeDuplicateNodes(ListNode head) {
            ListNode p =head;
             HashSet<Integer> set = new HashSet<>();
            ListNode newHead = new ListNode(-1);
            ListNode tail = newHead;
            while (p!=null){
                ListNode tmp = p.next;
                if (!set.contains(p.val)){
                    set.add(p.val);
                    tail.next =p;
                    tail=p;
                    tail.next=null;
                }
                p=tmp;
            }
        return  newHead.next;
    }
}
