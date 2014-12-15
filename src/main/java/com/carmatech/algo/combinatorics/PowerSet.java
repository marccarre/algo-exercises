package com.carmatech.algo.combinatorics;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.min;
import static java.util.Collections.unmodifiableList;

public class PowerSet {
    public static final int ALL_SUBSETS = -1;

    public static <T> Set<Set<T>> subsets(final Set<T> set) {
        return new PowerSetImpl(set, ALL_SUBSETS);
    }

    public static <T> Set<Set<T>> subsets(final Set<T> set, final int subsetsSize) {
        return new PowerSetImpl(set, subsetsSize);
    }

    private static int binomialCoefficient(final int n, int k) {
        if (k < 0 || k > n)
            return 0;
        if (k == 0 || k == n)
            return 1;
        k = min(k, n - k);
        int coefficient = 1;
        for (int i = 0; i < k; ++i)
            coefficient *= (n - i) / (i + 1);
        return coefficient;
    }

    private static class PowerSetImpl<T> extends AbstractSet<Set<T>> {
        private final List<T> elements;
        private final int powerSetSize;
        private final int subsetSize;
        private final int maxSize;

        public PowerSetImpl(final Set<T> set, final int subsetSize) {
            checkArgument(set.size() < 32, "Set is too large to compute power sets / subsets");
            this.elements = unmodifiableList(new ArrayList<T>(set));
            this.maxSize = maxSize(set.size());
            this.powerSetSize = calculateSize(set.size(), subsetSize);
            this.subsetSize = subsetSize;
        }

        private int calculateSize(final int setSize, final int subsetsOfSize) {
            if (subsetsOfSize == ALL_SUBSETS) {
                return maxSize(setSize);
            } else {
                return binomialCoefficient(setSize, subsetsOfSize);
            }
        }

        private int maxSize(final int setSize) {
            return 1 << setSize;
        }

        @Override
        public Iterator<Set<T>> iterator() {
            return new Iterator<Set<T>>() {
                private int position = -1;

                @Override
                public boolean hasNext() {
                    return position < maxSize;
                }

                @Override
                public Set<T> next() {
                    if (!hasNext())
                        throw new NoSuchElementException();
                    calculateNextPosition();
                    return new SubSet(position);
                }

                private void calculateNextPosition() {
                    if (subsetSize == ALL_SUBSETS) {
                        ++position;
                    } else {
                        ++position;
                        while (!isSubSetOfSize() && hasNext())
                            ++position;
                    }
                }

                private boolean isSubSetOfSize() {
                    return Integer.bitCount(position) == subsetSize;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public int size() {
            return powerSetSize;
        }

        private class SubSet extends AbstractSet<T> {
            private final int elementsMask;
            private final int subSetSize;

            SubSet(final int elementsMask) {
                this.elementsMask = elementsMask;
                this.subSetSize = Integer.bitCount(elementsMask);
            }

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private int remainingSetBits = elementsMask;

                    @Override
                    public boolean hasNext() {
                        return remainingSetBits != 0;
                    }

                    @Override
                    public T next() {
                        int index = Integer.numberOfTrailingZeros(remainingSetBits);
                        clearItem(index);
                        return elements.get(index);
                    }

                    private void clearItem(final int index) {
                        remainingSetBits &= ~(1 << index);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override
            public int size() {
                return subSetSize;
            }
        }
    }
}
