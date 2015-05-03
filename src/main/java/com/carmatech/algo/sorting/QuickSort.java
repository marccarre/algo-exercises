package com.carmatech.algo.sorting;

public class QuickSort {
    public static <T extends Comparable<T>> T[] dutchPartition(final T[] array) {
        return dutchPartition(array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> T[] dutchPartition(final T[] array, final int lo, final int hi) {
        final T pivot = array[lo];
        int smaller = lo;
        int equal = lo;
        int larger = hi;

        while (equal <= larger) {
            int compare = array[equal].compareTo(pivot);
            if (compare < 0) {
                swap(array, smaller++, equal++);
            } else if (compare > 0) {
                if ((equal < hi) && less(array, equal, larger)) {
                    // Keep partitioning stable if array already sorted:
                    swap(array, equal, equal + 1);
                } else {
                    swap(array, equal, larger--);
                }
            } else { // (compare == 0)
                ++equal;
            }
        }
        return array;
    }

    private static <T extends Comparable<T>> boolean less(final T[] array, final int i, final int j) {
        return array[i].compareTo(array[j]) < 0;
    }

    private static <T extends Comparable<T>> void swap(final T[] array, final int i, final int j) {
        final T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
