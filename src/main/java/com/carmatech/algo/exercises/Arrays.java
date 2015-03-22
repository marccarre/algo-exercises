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
}
