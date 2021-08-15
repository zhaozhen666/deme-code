package com.zhao.leetcode;


import java.util.ArrayList;
import java.util.List;

class reverseLinkList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        reverseList(head,list);
        int [] nums = new int[list.size()];
        int i=0;
        for (Integer num :list){
            nums[i++] =num;
        }
        return  nums;

    }
    public void reverseList(ListNode head,List<Integer> list){
        if (head==null){
            return;
        }
        reverseList(head.next, list);
        list.add(head.val);
    }
}