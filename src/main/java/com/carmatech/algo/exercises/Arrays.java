package com.carmatech.algo.exercises;

import java.util.Comparator;

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
}
