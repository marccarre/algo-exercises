package com.carmatech.algo.backtracking;

import com.google.common.collect.Sets;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Subsets {
    private Subsets() {
        // Pure utility class, do NOT instantiate.
    }

    private static boolean[] valueSpace = new boolean[] {false, true};

    public static Set<Set<Integer>> generate(final int n) {
        final Set<Set<Integer>> allSolutions = new LinkedHashSet<>();
        final Deque<Step> stack = new ArrayDeque<>();
        stack.push(Step.of(0, new boolean[n]));
        while (!stack.isEmpty()) {
            backtrack(stack, allSolutions, n);
        }
        return allSolutions;
    }

    private static void backtrack(final Deque<Step> stack, final Set<Set<Integer>> allSolutions, final int n) {
        final Step step = stack.pop();
        if (step.k == n) {
            allSolutions.add(createSubset(step.partialSolution));
        } else {
            for (final boolean value : valueSpace) {
                // make move.
                boolean previousValue = step.partialSolution[step.k];
                step.partialSolution[step.k] = value;

                stack.push(Step.of(step.k + 1, step.partialSolution.clone()));

                // unmake move (optional here).
                step.partialSolution[step.k] = previousValue;
            }
        }
    }
    
    private static class Step {
        private final int k;
        private final boolean[] partialSolution;

        private Step(final int k, final boolean[] partialSolution) {
            this.k = k;
            this.partialSolution = partialSolution;
        }
        
        public static Step of(final int k, final boolean[] partialSolution) {
            return new Step(k, partialSolution);
        }
    }

    public static Set<Set<Integer>> generateRecursively(final int n) {
        final boolean[] partialSolution = new boolean[n];
        final Set<Set<Integer>> allSolutions = Sets.newLinkedHashSet();
        backtrackRecursively(allSolutions, partialSolution, 0, n);
        return allSolutions;
    }

    private static void backtrackRecursively(final Set<Set<Integer>> allSolutions, final boolean[] partialSolution, int k, final int n) {
        if (k == n) {
            allSolutions.add(createSubset(partialSolution));
        } else {
            for (final boolean value : valueSpace) {
                // make move.
                boolean previousValue = partialSolution[k];
                partialSolution[k] = value;

                backtrackRecursively(allSolutions, partialSolution, k + 1, n);

                // unmake move (optional here).
                partialSolution[k] = previousValue;
            }
        }
    }

    private static Set<Integer> createSubset(final boolean[] partialSolution) {
        final Set<Integer> subset = Sets.newLinkedHashSet();
        for (int i = 0; i < partialSolution.length; ++i)
            if (partialSolution[i])
                subset.add(i + 1);
        return subset;
    }
}
