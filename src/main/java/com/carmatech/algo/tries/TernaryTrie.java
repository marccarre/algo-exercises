package com.carmatech.algo.tries;

import org.checkerframework.checker.nullness.qual.NonNull;

public class TernaryTrie<Value> implements Trie<Value> {
    private Node<Value> root;

    @Override
    public Value put(final String key, final Value value) {
        validate(key);
        final int length = key.length();
        final int last = length - 1;

        Node<Value> parent = null;
        Node<Value> node = getOrCreateRoot();

        int i = 0;
        char character = key.charAt(i);
        int compare = 0;

        while (i < length) {
            if (node == null)
                node = createNodeAndLinkTo(parent, compare);
            if (node.character == Character.UNASSIGNED) {
                node.character = character;
                if (i == last)
                    break;
                parent = node;
                node = node.mid;
                character = key.charAt(++i);
                continue;
            }

            compare = Character.compare(character, node.character);
            parent = node;
            node = next(node, compare);
            if (isMatch(compare) && (i++ < last))
                character = key.charAt(i);
        }

        // End of key reached, set new value and return previous one.
        if (node == null)
            node = createNodeAndLinkTo(parent, compare);
        return node.updateValue(value);
    }

    @Override
    public Value get(final String key) {
        validate(key);
        final int length = key.length();
        final int last = length - 1;

        Node<Value> node = root;
        int i = 0;
        char character = key.charAt(i);

        while ((node != null) && (i < length)) {
            int compare = Character.compare(character, node.character);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else if (i == last) {
                return node.value;
            } else {
                node = node.mid;
                character = key.charAt(++i);
            }
        }

        return null;
    }

    private void validate(final String key) {
        if (key == null)
            throw new NullPointerException("Key must NOT be null.");
        if (key.isEmpty())
            throw new IllegalArgumentException("Key must NOT be empty.");
    }

    @NonNull
    private Node<Value> getOrCreateRoot() {
        if (root == null)
            root = new Node<>();
        return root;
    }

    private Node<Value> createNodeAndLinkTo(final Node<Value> parent, final int compare) {
        final Node<Value> node = new Node<>();

        if (parent != null) {
            if (compare < 0) {
                parent.left = node;
            } else if (compare > 0) {
                parent.right = node;
            } else { // (compare == 0)
                parent.mid = node;
            }
        }

        return node;
    }

    private Node<Value> next(final Node<Value> node, final int compare) {
        if (compare < 0) {
            return node.left;
        } else if (compare > 0) {
            return node.right;
        } else { // (compare == 0)
            return node.mid;
        }
    }

    private boolean isMatch(final int compare) {
        return compare == 0;
    }

    private class Node<Type> {
        private Node<Type> left, mid, right;
        private char character;
        private Type value;

        public Type updateValue(final Type newValue) {
            final Type previousValue = value;
            value = newValue;
            return previousValue;
        }

        @Override
        public String toString() {
            return character + " -> " + value;
        }
    }
}
