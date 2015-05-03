package com.carmatech.algo.collections.trees;

public class BinaryTree<T> {
    public static class Node<Value> {
        public Value value;
        public Node<Value> leftChild;
        public Node<Value> rightChild;

        public Node(final Value value) {
            this.value = value;
        }

        public Node(final Value value, final Node<Value> leftChild, final Node<Value> rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    private Node<T> root;

    public BinaryTree() {
    }

    public BinaryTree(final Node<T> root) {
        this.root = root;
    }

    public boolean isBalanced() {
        if (root == null)
            return true;
        final int heightLeftSubTree  = heightOf(root.leftChild,  0);
        final int heightRightSubTree = heightOf(root.rightChild, 0);
        return Math.abs(heightRightSubTree - heightLeftSubTree) <= 1;
    }

    private int heightOf(final Node<T> node, final int height) {
        if (node == null)
            return height;
        final int heightLeftSubTree  = heightOf(node.leftChild,  height + 1);
        final int heightRightSubTree = heightOf(node.rightChild, height + 1);
        return Math.max(heightLeftSubTree, heightRightSubTree);
    }
}
