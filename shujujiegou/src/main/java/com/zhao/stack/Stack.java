package com.zhao.stack;

public interface Stack<E> {
    public int getSize();
    public void push(E e);
    public E pop();
    public E peek();
    boolean isEmpty();
}
