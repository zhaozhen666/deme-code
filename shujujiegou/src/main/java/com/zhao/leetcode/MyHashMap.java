package com.zhao.leetcode;

import com.zhao.linkedlist.LinkedList;

public class MyHashMap {
    public class Pair{
        public int key;
        public int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }
    private static final  int  SLOTS_COUNT =3535;
    private LinkedList<Pair> [] slots;

    private int hash(int key){
        return key%SLOTS_COUNT;
    }

    public MyHashMap() {
        this.slots = new LinkedList[SLOTS_COUNT];
    }
    public void put(int key,int value){
        LinkedList<Pair> slot =slots[hash(key)];
        if (slot==null){
            slots[hash(key)] = new LinkedList<>();
            slot =slots[hash(key)];
        }
        for (int i=0;i<slot.getSize();++i){
            Pair pair= slot.get(i);
            if (pair.key==key){
                slot.remove(i);
                break;
            }
        }
        slot.addLast(new Pair(key,value));
    }

    public int get(int key){
        LinkedList<Pair> slot =slots[hash(key)];
        if (slot==null){
            return -1;
        }
        for (int i=0;i<slot.getSize();++i){
            Pair pair= slot.get(i);
            if (pair.key==key){
                return pair.value;
            }
        }
        return -1;

    }
    public void  remove(int key){
        LinkedList<Pair> slot =slots[hash(key)];
        if (slot==null){
            return ;
        }
        for (int i=0;i<slot.getSize();++i){
            Pair pair= slot.get(i);
            if (pair.key==key){
                slot.remove(i);
                break;
            }
        }
    }
}
