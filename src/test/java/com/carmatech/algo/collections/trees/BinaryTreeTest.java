package com.carmatech.algo.collections.trees;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BinaryTreeTest {
    @Test
    public void isBalancedShouldReturnTrueForEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertThat(tree.isBalanced(), is(true));
    }

    @Test
    public void isBalancedShouldReturnTrueForPerfectlyBalancedTree() {
        BinaryTree<Integer> tree = new BinaryTree<>(
                new BinaryTree.Node<>(
                        1,
                        new BinaryTree.Node<>(2),
                        new BinaryTree.Node<>(3)
                )
        );
        assertThat(tree.isBalanced(), is(true));
    }

    @Test
    public void isBalancedShouldReturnTrueWhenDifferenceInHeightIsOne() {
        BinaryTree<Integer> tree = new BinaryTree<>(
                new BinaryTree.Node<>(
                        1,
                        new BinaryTree.Node<>(2),
                        new BinaryTree.Node<>(
                                3,
                                new BinaryTree.Node<>(4),
                                null
                        )
                )
        );
        assertThat(tree.isBalanced(), is(true));
    }

    @Test
    public void isBalancedShouldReturnFalseOtherwise() {
        BinaryTree<Integer> tree = new BinaryTree<>(
                new BinaryTree.Node<>(
                        1,
                        new BinaryTree.Node<>(2),
                        new BinaryTree.Node<>(
                                3,
                                new BinaryTree.Node<>(
                                        4,
                                        new BinaryTree.Node<>(5),
                                        null
                                ),
                                null
                        )
                )
        );
        assertThat(tree.isBalanced(), is(false));
    }
}
