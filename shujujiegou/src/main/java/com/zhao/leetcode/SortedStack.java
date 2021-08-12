package com.zhao.leetcode;


import java.util.Stack;

class SortedStack {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public SortedStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int val) {
        while (!stack1.isEmpty()&&stack1.peek()<val){
            stack2.push(stack1.pop());
        }
        stack1.push(val);
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }
    
    public void pop() {
        if (!stack1.isEmpty()){
            stack1.pop();
        }
    }
    
    public int peek() {
            if (stack1.isEmpty())
                return -1;
          return   stack1.peek();
    }
    
    public boolean isEmpty() {
        return stack1.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */