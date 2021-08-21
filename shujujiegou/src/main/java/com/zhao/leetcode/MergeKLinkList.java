package com.zhao.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLinkList {
      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    private class QElement{
          ListNode curNode;

        public QElement(ListNode curNode) {
            this.curNode = curNode;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;
        int k =lists.length;

        PriorityQueue<QElement> minQ = new PriorityQueue<>(new Comparator<QElement>() {
            @Override
            public int compare(QElement q1, QElement q2) {
                return q1.curNode.val-q2.curNode.val;
            }
        });
        for (int i=0;i<k;++i){
            if (lists[i]!=null){
                minQ.offer(new QElement(lists[i]));
            }
        }
        ListNode head = new ListNode();
        ListNode tail =head;
        while (!minQ.isEmpty()){
            QElement element = minQ.poll();
            ListNode curNode = element.curNode;
            tail.next = element.curNode;
            tail=tail.next;
            if (curNode.next!=null){
                minQ.offer(new QElement(curNode.next));
            }
        }
        return head.next;
    }
}
