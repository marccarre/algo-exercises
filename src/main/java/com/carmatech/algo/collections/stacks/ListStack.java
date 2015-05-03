package com.carmatech.algo.collections.stacks;

import java.util.EmptyStackException;

public class ListStack<T> {
    private Node<T> top = null;

    private class Node<T> {
        private final Node<T> next;
        private final T value;
        Node(final Node<T> next, final T value) {
            this.next = next;
            this.value = value;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(final T value) {
        top = new Node(top, value);
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        final T value = top.value;
        top = top.next;
        return value;
    }
}
