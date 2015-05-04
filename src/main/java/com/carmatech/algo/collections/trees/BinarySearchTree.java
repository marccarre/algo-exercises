package com.carmatech.algo.collections.trees;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(final Node<K, V> root) {
        this.root = root;
    }

    public V get(final K key) {
        checkNotNull(key, "Key must NOT be null.");
        Node<K, V> node = root;
        while (node != null) {
            int compare = node.key.compareTo(key);
            if (compare == 0) {
                return node.value;
            } else if (compare < 0) {
                node = node.leftChild;
            } else { // (compare > 0)
                node = node.rightChild;
            }
        }
        return null;
    }

    public V put(final K key, final V value) {
        checkNotNull(key, "Key must NOT be null.");
        checkNotNull(value, "Value must NOT be null.");

        if (root == null) {
            root = new Node<>(key, value);
            return null; // No previous value.
        }

        int compare = 0;
        Node<K, V> previous = null;
        Node<K, V> node = root;
        while (node != null) {
            compare = key.compareTo(node.key);
            if (compare == 0) {
                return node.updateValue(value); // Replace value for existing key.
            } else if (compare < 0) {
                previous = node;
                node = node.leftChild;
            } else { // (compare > 0)
                previous = node;
                node = node.rightChild;
            }
        }
        // No node with provided key, we add it:
        return addNewNode(previous, compare, key, value);
    }

    private V addNewNode(final Node<K, V> previous, final double compare, final K key, final V value) {
        if (compare < 0) {
            previous.leftChild = new Node<>(key, value);
        } else { // (compare > 0)
            previous.rightChild = new Node<>(key, value);
        }
        return null; // No previous value for this key.
    }

    public V min() {
        if (root == null) {
            return null;
        }

        Node<K, V> node = root;
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node.value;
    }

    public V max() {
        if (root == null) {
            return null;
        }

        Node<K, V> node = root;
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node.value;
    }

    public boolean isValid() {
        final Map<K, K> minimums = new HashMap<>();
        final Map<K, K> maximums = new HashMap<>();
        return isValid(root, minimums, maximums);
    }

    private boolean isValid(final Node<K, V> node,  final Map<K, K> minimums, final Map<K, K> maximums) {
        if (node == null)
            return true;

        final K min = findMinimum(node.rightChild, minimums);
        if ((min != null) && !smaller(node.key, min))
            return false;

        final K max = findMaximum(node.leftChild, maximums);
        if ((max != null) && !greater(node.key, max))
            return false;

        return isValid(node.leftChild, minimums, maximums) &&
               isValid(node.rightChild, minimums, maximums);
    }

    private K findMinimum(final Node<K, V> node, final Map<K, K> minimums) {
        if (node == null)
            return null;

        K minKey = minimums.get(node.key);
        if (minKey != null)
            return minKey;

        final K leftMin  = findMinimum(node.leftChild, minimums);
        final K rightMin = findMinimum(node.rightChild, minimums);
        minKey = smaller(leftMin, rightMin) ? leftMin : rightMin;
        minKey = smaller(node.key, minKey) ? node.key : minKey;
        minimums.put(node.key, minKey); // Cache minimum for this sub-tree.
        return minKey;
    }

    private K findMaximum(final Node<K, V> node, final Map<K, K> maximums) {
        if (node == null)
            return null;

        K maxKey = maximums.get(node.key);
        if (maxKey != null)
            return maxKey;

        final K leftMax  = findMaximum(node.leftChild, maximums);
        final K rightMax = findMaximum(node.rightChild, maximums);
        maxKey = greater(leftMax, rightMax) ? leftMax : rightMax;
        maxKey = greater(node.key, maxKey) ? node.key : maxKey;
        maximums.put(node.key, maxKey); // Cache maximum for this sub-tree.
        return maxKey;
    }

    private boolean smaller(final K a, final K b) {
        return compare(a, b) < 0;
    }

    private boolean greater(final K a, final K b) {
        return compare(a, b) > 0;
    }

    private int compare(final K a, final K b) {
        if (a == null)
            return -1;
        if (b == null)
            return 1;
        return a.compareTo(b);
    }

    public static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> leftChild;
        private Node<Key, Value> rightChild;
        private String toString;

        public Node(final Key key, final Value value, final Node<Key, Value> leftChild, final Node<Key, Value> rightChild) {
            this.key = key;
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Node(final Key key, final Value value) {
            this(key, value, null, null);
        }

        public Value updateValue(final Value newValue) {
            final Value oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public String toString() {
            if (toString == null)
                toString = "(" + key + " -> " + value + ")";
            return toString;
        }
    }
}
