package com.zhao.leetcode;


import java.util.Stack;

class MyQueue {
    Stack<Integer> a, b;
    public MyQueue() {
        a = new Stack();
        b = new Stack();
    }

    public void push(int x) {
        while(!b.isEmpty()) {
            a.push(b.peek());
            b.pop();
        }
        a.push(x);
        while(!a.isEmpty()) {
            b.push(a.peek());
            a.pop();
        }
    }

    public int pop() {
        return b.pop();
    }

    public int peek() {
        return b.peek();
    }

    public boolean empty() {
        return b.isEmpty();
    }
}