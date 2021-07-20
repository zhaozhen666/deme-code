package com.zhao.linkedlist;

public class LinkedList<E> {
    private Node dummyHead;
    private int  size;

    public LinkedList(){
            dummyHead=new Node();
            size=0;
    }
    public boolean isEmpty(){
        return  size==0;
    }
    public int getSize(){
        return size;
    }


    public void  add(int index,E e){
        if (index<0||index>size){
            throw  new IllegalArgumentException(" 错误的位置");
        }

        Node prev = dummyHead;
        for (int i=0;i<index;i++){
            prev =prev.next;
        }
        prev.next = new Node(e,prev.next);
        size++;

    }

    public E remove(int index){
        if (index<0||index>size){
            throw  new IllegalArgumentException(" 错误的位置");
        }
        Node prev = dummyHead;
        for (int i = 0;i<index;i++){
            prev =prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next=null;
        size--;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }
    public void addFirst(E e){
        add(0,e);
    }
    public void addLast(E e){
        add(size,e);
    }

    public E get(int index){
        if (index<0||index>size){
            throw  new IllegalArgumentException(" 错误的位置");
        }
        Node cur = dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.e;
    }

    public void set(E e,int index){
        if (index<0||index>size){
            throw  new IllegalArgumentException(" 错误的位置");
        }
        Node cur = dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.e=e;
    }

    public boolean contains(E e ){
        Node cur = dummyHead.next;
        while (cur!=null){
            if (cur.e.equals(e)){
                return true;
            }
            cur= cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur!=null){
            builder.append(cur+"->");
            cur= cur.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }
    private class Node{
        public E e;
        public Node next;
        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e+"";
        }
    }


}
