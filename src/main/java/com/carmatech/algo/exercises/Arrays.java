package com.carmatech.algo.exercises;

public class Arrays {
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
}
