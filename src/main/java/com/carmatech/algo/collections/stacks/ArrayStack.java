package com.carmatech.algo.collections.stacks;

import java.util.EmptyStackException;

public class ArrayStack<T> {
    private T[] stack;
    private int top = 0;

    public ArrayStack() {
        this(32);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(final int size) {
        if (size < 1)
            throw new IllegalArgumentException();
        stack = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(final T value) {
        if (isFull())
            doubleSize();
        stack[top++] = value;
    }

    private void doubleSize() {
        resize(stack.length << 1, stack.length);
    }

    private boolean isFull() {
        return top == stack.length;
    }

    @SuppressWarnings("unchecked")
    private void resize(final int size, final int max) {
        final T[] newStack = (T[]) new Object[size];
        System.arraycopy(stack, 0, newStack, 0, max);
        stack = newStack;
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        final T value = stack[--top];
        stack[top] = null; // Prevents loitering / memory leak.
        if (isQuarterFull())
            halveSize();
        return value;
    }

    private void halveSize() {
        resize(stack.length >>> 1, top);
    }

    private boolean isQuarterFull() {
        return top == (stack.length >>> 2);
    }
}
