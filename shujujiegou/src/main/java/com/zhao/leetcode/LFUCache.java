package com.zhao.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    private class DLinkedNode{
        Integer key;
        Integer value;
        Integer count;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(Integer key,Integer value,Integer count){
            this.key=key;
            this.value =value;
            this.count = count;
        }

    }

    private Map<Integer,DLinkedNode> hashMap = new HashMap<Integer,DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head;
    private DLinkedNode tail;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size=0;
        this.head =new DLinkedNode(-1,-1,Integer.MAX_VALUE);
        this.tail =new DLinkedNode(-1,-1,0);
        this.head.prev =null;
        this.tail.next =null;
        this.head.next =tail;
        this.tail.prev =head;
    }
    private  void removeNode(DLinkedNode node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    private void addByCount(DLinkedNode node){
        int count = node.count;
        DLinkedNode p = this.head;
        DLinkedNode pre =null;
        while (p!=null){
            if (count>=p.count){
                break;
            }
            pre=p;
            p=p.next;
        }
        node.next =pre.next;
        pre.next.prev =node;

        pre.next =node;
        node.prev=pre;

    }

    public int get(int key){
        if (this.size==0) return -1;
        DLinkedNode node = hashMap.get(key);
        if (node==null) return -1;
        node.count++;
        removeNode(node);
        addByCount(node);
        return node.value;
    }

    public void  put(int key,int value){
        if (this.capacity==0) return;
        DLinkedNode node = hashMap.get(key);
        DLinkedNode newNode = new DLinkedNode(key,value,0);
        if (node!=null){
            newNode.count =node.count+1;
            node.count=0;
            removeNode(node);
            addByCount(newNode);
            hashMap.put(key,newNode);
        }else {
            if (this.capacity==size){
                size--;
                tail.prev.count =0;
                hashMap.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            newNode.count=1;
            addByCount(newNode);
            hashMap.put(key,newNode);
            size++;
        }
    }
}
