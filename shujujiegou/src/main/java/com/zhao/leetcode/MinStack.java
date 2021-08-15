package com.zhao.leetcode;

import java.util.Stack;

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {

        if (stack.isEmpty()&&minStack.isEmpty()){
            stack.push(val);
            minStack.push(val);

        }else {
            stack.push(val);
            if (val<minStack.peek()){
                minStack.push(val);
            }else {
                minStack.push(minStack.peek());
            }
        }
    }
    
    public void pop() {
            stack.pop();
            minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
         return    minStack.peek();
    }
}
