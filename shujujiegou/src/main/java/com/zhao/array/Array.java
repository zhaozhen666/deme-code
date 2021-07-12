package com.zhao.array;

import java.sql.DatabaseMetaData;
import java.util.Calendar;

public class Array <E>{
    private int size;
    private E[] data;

    public Array(int capacity){
        data=(E[])new  Object[capacity];
        size=0;
    }

    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }
    public int getCapcity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size ==0;
    }

    public void addFirst(E e){
        add(0,e);
    }
    public void  addLast(E e){
        add(size,e);
    }

    public E get(int index){
        if (index<0||index>size){
            throw  new IllegalArgumentException("位置不合法");
        }
        return data[index];
    }

    public void set(int index,E e){
        if (index<0||index>size){
            throw  new IllegalArgumentException("位置不合法");
        }
        data[index]=e;
    }
    public void  add(int index,E e){
        if (size==data.length)
            //Java扩容是1.5
            resize(data.length*2);
        if (index<0||index>size)
            throw  new IllegalArgumentException("位置不合法");
        for (int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData= (E[]) new Object[newCapacity];
        for (int i=0;i<data.length;i++){
            newData[i] =data[i];
        }
        data=newData;
    }


    public boolean contains(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }
    public int find(E e){
        for (int i=0;i<size;i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if (index<0||index>size)
            throw  new IllegalArgumentException("位置不合法");
        E ret =data[index];
        for (int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;
        if (size==data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }


    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){
        int index = find(e);
        if (index!=-1){
            remove(index);
        }
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array:size=%d,capacity=%d\n",size,data.length));
        builder.append("[");
        for (int i=0;i<size;i++){
            builder.append(data[i]);
            if (i<size-1){
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
