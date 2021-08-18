package com.zhao.leetcode;

import java.util.HashSet;

public class CycleList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }
    //CycleList 141
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p =head;
        while (p!=null){
            if (set.contains(p)){
                return true;
            }else {
                set.add(p);
            }
            p=p.next;
        }
        return false;
    }

    // 022
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p =head;
        while (p!=null){
            if (set.contains(p)){
                return p;
            }else {
                set.add(p);
            }
            p=p.next;
        }
        return null;
    }
}
