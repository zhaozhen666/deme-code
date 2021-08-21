package com.zhao.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//347
public class TopK {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    private class QElement{
        int count;
        int val;

        public QElement(int val, int count) {
            this.count = count;
            this.val = val;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> counts = new HashMap<>();
        for (int num :nums){
            counts.put(num,counts.getOrDefault(num,0)+1);

        }
        PriorityQueue<QElement> queue = new PriorityQueue<>(new Comparator<QElement>() {
            @Override
            public int compare(QElement q1, QElement q2) {
                return q1.count-q2.count;
            }
        });
        for (Map.Entry<Integer,Integer> entry:counts.entrySet()){
            int num =entry.getKey();
            int count =entry.getValue();
            if (queue.size()<k){
                queue.offer(new QElement(num,count));
            }else {
                if (queue.peek().count<count){
                    queue.poll();
                    queue.offer(new QElement(num,count));
                }
            }
        }
        int [] result = new int[k];
        for (int i=0;i<k;i++){
            result[i] = queue.poll().val;

        }
        return result;
    }
}
