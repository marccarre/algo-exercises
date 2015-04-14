package com.carmatech.algo.dynprog;

import java.util.ArrayList;
import java.util.List;

public class Rod {
    private final int[] prices;

    public Rod(final int[] prices) {
        if (prices == null)
            throw new NullPointerException("Prices must NOT be null.");
        if (prices.length == 0)
            throw new IllegalArgumentException("Prices must NOT be empty.");
        this.prices = prices;
    }

    public int maxPrice(final int rodLength) {
        if (rodLength < 0 || rodLength >= prices.length)
            throw new IllegalArgumentException("Rod length must be between 0 and " + (prices.length - 1) + " but was " + rodLength);

        final int[] maxPrices = new int[rodLength + 1];

        for (int length = 1; length <= rodLength; ++length) { // Sub-problems' length.
            maxPrices[length] = prices[length];
            for (int i = 0; i <= ((length + 1) / 2); ++i) { // Exploit symmetry of problem.
                int price = maxPrices[i] + maxPrices[length - i];
                if (price > maxPrices[length])
                    maxPrices[length] = price;
            }
        }

        return maxPrices[rodLength];
    }

    public List<Integer> cutForMaxPrice(final int rodLength) {
        if (rodLength < 0 || rodLength >= prices.length)
            throw new IllegalArgumentException("Rod length must be between 0 and " + (prices.length - 1) + " but was " + rodLength);


        final int[] maxCut = new int[rodLength + 1];
        final int[] maxPrices = new int[rodLength + 1];

        for (int length = 1; length <= rodLength; ++length) { // Sub-problems' length.
            maxPrices[length] = prices[length];
            maxCut[length] = length;
            for (int i = 0; i <= ((length + 1) / 2); ++i) { // Exploit symmetry of problem.
                int price = maxPrices[i] + maxPrices[length - i];
                if (price > maxPrices[length]) {
                    maxPrices[length] = price;
                    maxCut[length] = i;
                }
            }
        }


        final List<Integer> cut = new ArrayList<>();
        int length = rodLength;
        while (length != 0) {
            cut.add(maxCut[length]);
            length -= maxCut[length];
        }

        return cut;
    }
}
