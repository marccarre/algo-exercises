package com.carmatech.algo.collections.stacks;

import java.util.EmptyStackException;

public class MaxAwareListStack<T extends Comparable<T>> {
    private class Entry<Value> {
        private final Value value;
        private final Value max;
        private final Entry<Value> next;

        public Entry(final Value value, final Value max, final Entry<Value> next) {
            this.value = value;
            this.max = max;
            this.next = next;
        }
    }

    private Entry<T> top;

    public boolean isEmpty() {
        return top == null;
    }

    public MaxAwareListStack<T> push(final T value) {
        top = new Entry<>(value, max(value, top), top);
        return this;
    }

    private T max(final T a, final Entry<T> b) {
        if (a == null)
            return (b == null) ? null : b.max;
        if (b == null || b.max == null)
            return a;
        return (a.compareTo(b.max) > 0) ? a : b.max;
    }

    public T max() {
        return isEmpty() ? null : top.max;
    }

    public T peek() {
        return isEmpty() ? null : top.value;
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        final T value = top.value;
        top = top.next;
        return value;
    }
}
