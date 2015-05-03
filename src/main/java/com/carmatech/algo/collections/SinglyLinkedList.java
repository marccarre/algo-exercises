package com.carmatech.algo.collections;

public class SinglyLinkedList<T extends Comparable<T>> {
    private class Node<Value> {
        private Node<Value> next;
        private Value value;

        public Node() {
        }

        public Node(final Value value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public SinglyLinkedList<T> add(final T value) {
        if (head == null) {
            head = new Node<T>(value);
            tail = head;
        } else {
            tail.next = new Node<T>(value);
            tail = tail.next;
        }
        return this;
    }

    public SinglyLinkedList<T> merge(final SinglyLinkedList<T> that) {
        if (that == null || that.head == null)
            return this;

        if (this.head == null)
            return that;

        Node<T> thisNode = this.head;
        Node<T> thatNode = that.head;
        Node<T> merged = new Node<T>();
        Node<T> headMerged = merged;

        while (thisNode != null || thatNode != null) {
            if (thisNode == null) {
                merged.next = thatNode;
                break;
            } else if (thatNode == null) {
                merged.next = thisNode;
                break;
            } else { // both nodes are non-null:
                if (less(thatNode, thisNode)) {
                    merged.next = thatNode;
                    thatNode = thatNode.next;
                } else { // thisNode.value <= thatNode.value:
                    merged.next = thisNode;
                    thisNode = thisNode.next;
                }
                merged = merged.next;
            }
        }

        head = headMerged.next;
        return this;
    }

    private boolean less(final Node<T> a, final Node<T> b) {
        return a.value.compareTo(b.value) < 0;
    }

    @Override
    public String toString() {
        final StringBuilder string = new StringBuilder();
        string.append("[");
        Node<T> node = head;
        while (node != null) {
            string.append(node.value);
            if (node.next != null)
                string.append(", ");
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
