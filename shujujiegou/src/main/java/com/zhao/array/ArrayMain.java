package com.zhao.array;

public class ArrayMain {
    public static void main(String[] args) {
        Array array =new Array(20);
        for (int i=0;i<10;i++){
            array.addLast(i);
        }
        System.out.println(array);
    }
}
