package com.zhao.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private class DLinkedNode{
         Integer key;
         Integer value;
         DLinkedNode prev;
         DLinkedNode next;
        public DLinkedNode(Integer key,Integer value){
            this.key=key;
            this.value =value;
        }

    }
    private Map<Integer,DLinkedNode> hashMap = new HashMap<Integer,DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size=0;
        this.head =new DLinkedNode(-1,-1);
        this.tail =new DLinkedNode(-1,-1);
        this.head.prev =null;
        this.tail.next =null;
        this.head.next =tail;
        this.tail.prev =head;
    }

    public int get(String key){
        if (size==0){
            return -1;
        }
        DLinkedNode node = hashMap.get(key);
        if (node==null){
            return -1;
        }
        removeNode(node);
        addNodeAtHead(node);
        return node.value;
    }
    public void  remove(int key){
        DLinkedNode node = hashMap.get(key);
        if (node!=null){
            removeNode(node);
            hashMap.remove(key);
            return;
        }
    }
    private  void removeNode(DLinkedNode node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    private void addNodeAtHead(DLinkedNode node){
        node.next =head.next;
        head.next.prev=node;
        head.next=node;
        node.prev=head;
    }

    public void put(int key ,int value){
        DLinkedNode node = hashMap.get(key);
        if (node!=null){
            node.value=value;
            removeNode(node);
            addNodeAtHead(node);
            return;
        }
        if (size==capacity){
            hashMap.remove(tail.prev.key);
            removeNode(tail.prev);
            size--;
        }
        DLinkedNode newNode = new DLinkedNode(key,value);
        addNodeAtHead(newNode);
        hashMap.put(key,newNode);
        size++;
    }
}
