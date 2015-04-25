package com.carmatech.algo.tries;

import com.carmatech.algo.utilities.IVisitable;

import java.util.function.Function;

public class DynamicRWayTrie<Value> implements IVisitable<DynamicRWayTrie<Value>>, Trie<Value> {
    private final Node<Value> root = new Node<>();

    @Override
    public Value get(final String key) {
        validate(key);
        final int length = key.length();
        final int lastIndex = length - 1;

        Node<Value> node = root;
        for (int i = 0; i < length; ++i) {
            node = node.getChild(key.charAt(i));
            if (node == null)
                return null;
            if (i == lastIndex) {
                return node.value;
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public Value put(final String key, final Value value) {
        validate(key);
        validate(value);
        final int lastIndex = key.length() - 1;

        Value previousValue = null;
        Node<Value> node = root;
        for (int i = 0; i < key.length(); ++i) {
            final Node<Value> next = getOrCreateChildFor(key.charAt(i), node);
            if (i == lastIndex) {
                previousValue = updateValue(value, next);
                break;
            }
            node = next;
        }
        return previousValue;
    }

    private Node<Value> getOrCreateChildFor(final char currentChar, final Node<Value> node) {
        Node<Value> next = node.getChild(currentChar);
        if (next == null) {
            next = new Node<>();
            node.putChild(currentChar, next);
        }
        return next;
    }

    private Value updateValue(final Value value, final Node<Value> next) {
        final Value previous = next.value;
        next.value = value;
        return previous;
    }

    private void validate(final Value value) {
        if (value == null)
            throw new NullPointerException("Value must NOT be null.");
    }

    private void validate(final String key) {
        if (key == null)
            throw new NullPointerException("Key must NOT be null.");
        if (key.isEmpty())
            throw new IllegalArgumentException("Key must NOT be empty.");
    }

    @Override
    public <Out> Out visit(final Function<DynamicRWayTrie<Value>, Out> visitor) {
        return visitor.apply(this);
    }

    private static class Node<T> {
        private static final int RADIX = 256;
        //private final Map<Character, Node<T>> children = new HashMap<>();
        private final Node<T>[] children = new Node[RADIX];
        private T value = null;

        public Node<T> getChild(final char key) {
            return children[key];
            //return children.get(key);
        }

        public void putChild(final char key, final Node<T> child) {
            children[key] = child;
            //children.put(key, child);
        }
    }
}
