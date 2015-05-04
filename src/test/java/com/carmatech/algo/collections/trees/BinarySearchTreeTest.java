package com.carmatech.algo.collections.trees;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BinarySearchTreeTest {
    private final BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();

    @Test
    public void getOnEmptyTreeShouldReturnNull() {
        assertThat(bst.get(42), is(nullValue()));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void getOnSingletonTreeShouldReturnAddedValue() {
        assertThat(bst.put(42, 1042), is(nullValue()));
        assertThat(bst.get(42),       is(1042));
        assertThat(bst.get(41),       is(nullValue()));
        assertThat(bst.get(43),       is(nullValue()));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void putOnExistingKeyShouldReturnPreviousValueAndStoreNewValue() {
        assertThat(bst.put(42, 1042), is(nullValue()));
        assertThat(bst.put(42, 1040), is(1042));
        assertThat(bst.get(42),       is(1040));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void minAndMaxOnEmptyTreeShouldReturnNull() {
        assertThat(bst.min(), is(nullValue()));
        assertThat(bst.max(), is(nullValue()));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void minAndMaxOnSingletonTreeShouldReturnAddedValue() {
        assertThat(bst.put(42, 1042), is(nullValue()));
        assertThat(bst.min(),         is(1042));
        assertThat(bst.max(),         is(1042));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void maxOnDeeperTreeShouldReturnMaxValue() {
        assertThat(bst.put(42, 1042), is(nullValue()));
        assertThat(bst.put(40, 1040), is(nullValue()));
        assertThat(bst.put(44, 1044), is(nullValue()));
        assertThat(bst.put(39, 1039), is(nullValue()));
        assertThat(bst.put(41, 1041), is(nullValue()));
        assertThat(bst.put(45, 1045), is(nullValue()));
        assertThat(bst.put(43, 1043), is(nullValue()));
        assertThat(bst.min(),         is(1039));
        assertThat(bst.max(),         is(1045));
        assertThat(bst.isValid(), is(true));
    }

    @Test
    public void isValidShouldReturnFalseIsBSTPropertyDoesNotHold() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>(
                new BinarySearchTree.Node<>(42, 1042,
                        new BinarySearchTree.Node<>(39, 1039,
                                null,
                                new BinarySearchTree.Node<>(44, 1044)
                        ),
                        new BinarySearchTree.Node<>(45, 1045)
                )
        );
        assertThat(bst.isValid(), is(false));
    }

    @Test
    public void isValidShouldReturnTrueIsBSTPropertyHolds() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>(
                new BinarySearchTree.Node<>(42, 1042,
                        new BinarySearchTree.Node<>(39, 1039,
                                null,
                                new BinarySearchTree.Node<>(41, 1041)
                        ),
                        new BinarySearchTree.Node<>(45, 1045)
                )
        );
        assertThat(bst.isValid(), is(true));
    }
 }
