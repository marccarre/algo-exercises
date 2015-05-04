package com.carmatech.algo.exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Arrays {
    private Arrays() {
        // Pure utility class, do NOT instantiate.
    }

    public static int[][] rotate90Degrees(final int[][] image) {
        final int n = image.length;
        final int numLayers = (image.length + 1) / 2;
        int tmp;
        for (int i = 0; i < numLayers; ++i) {
            for (int j = i; j < n-1-i; ++j) {
                tmp = image[i][j];
                image[i][j]         = image[n-1-j][i];
                image[n-1-j][i]     = image[n-1-i][n-1-j];
                image[n-1-i][n-1-j] = image[j][n-1-i];
                image[j][n-1-i]     = tmp;
            }
        }
        return image;
    }

    public static int[][] spiral(final int n) {
        final int[][] spiral = new int[n][n];
        final int numLayers = (n + 1) / 2;
        int k = 1;
        for (int layer = 0; layer < numLayers; ++layer) {
            for (int i = layer; i < n-layer; ++i) // top
                spiral[layer][i] = k++;
            for (int i = layer+1; i < n-layer; ++i) // right
                spiral[i][n-layer-1] = k++;
            for (int i = n-layer-2; i >= layer; --i) // bottom
                spiral[n-layer-1][i] = k++;
            for (int i = n-layer-2; i > layer; --i) // left
                spiral[i][layer] = k++;
        }
        return spiral;
    }

    public static int lowerBoundarySearch(final int[] array, final int target) {
        if (array == null || array.length == 0)
            return -1;
        int k = array.length / 2;
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            if ((array[k] == target) && ((k == 0) || (array[k - 1] != target))) {
                return k;
            } else if (array[k] >= target){
                hi = k - 1;
                k = lo + (hi - lo) / 2;
            } else /* (array[k] < target) */ {
                lo = k + 1;
                k = lo + (hi - lo) / 2;
            }
        }
        return -1;
    }

    public static int upperBoundarySearch(final int[] array, final int target) {
        if (array == null || array.length == 0)
            return -1;
        int k = array.length / 2;
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            if ((array[k] == target) && ((k == array.length - 1) || (array[k + 1] != target)))
                return k;
            else if (array[k] <= target) {
                lo = k + 1;
                k = lo + (hi - lo) / 2;
            } else /* (array[k] > target) */ {
                hi = k - 1;
                k = lo + (hi - lo) / 2;
            }
        }
        return -1;
    }

    public static int count(final int[] array, final int target) {
        int begin = lowerBoundarySearch(array, target);
        if (begin == -1)
            return 0;
        int end = upperBoundarySearch(array, target);
        return end - begin + 1;
    }

    private static final int NOT_FOUND = -1;

    public static <T extends Comparable<T>> int binarySearch(final T target, final T[] array, final Comparator<T> comparator) {
        if (array == null || array.length == 0 || target == null || comparator == null)
            return NOT_FOUND;

        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(target, array[mid]);
            if (compare == 0)
                return mid;
            else if (compare < 0)
                hi = mid - 1;
            else // (compare > 0)
                lo = mid + 1;
        }

        return NOT_FOUND;
    }

    public static <T extends Comparable<T>> int binarySearch(final T target, final T[] array) {
        return binarySearch(target, array, (first, second) -> first.compareTo(second));
    }

    public static int[] intersection(final int[] a, final int[] b) {
        if (a == null || a.length == 0 || b == null || b.length == 0) {
            return new int[0];
        }

        final List<Integer> intersection = new ArrayList<>();
        int i = 0;
        int j = 0;
        while ((i < a.length) && (j < b.length)) {
            if (a[i] == b[j]) {
                if (isFirstOccurrence(i, a)) {
                    intersection.add(a[i]);
                }
                ++i;
                ++j;
            } else if (a[i] < b[j]) {
                ++i;
            } else if (a[i] > b[j]) {
                ++j;
            }
        }
        return toArray(intersection);
    }

    private static boolean isFirstOccurrence(final int i, final int[] array) {
        return (i == 0) || (array[i] != array[i - 1]);
    }

    private static int[] toArray(final List<Integer> list) {
        final int[] array = new int[list.size()];
        int i = 0;
        for (final int element : list) {
            array[i++] = element;
        }
        return array;
    }

    /*
     * O(n^2)
     */
    public static int maxSubArraySumBruteForce(final int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        for (int from = 0; from < array.length; ++from) {
            int sum = array[from];
            if (max < sum)
                max = sum;
            for (int to = from + 1; to < array.length; ++to) {
                sum += array[to];
                if (max < sum)
                    max = sum;
            }
        }
        return max;
    }

}
