package com.carmatech.algo.backtracking;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class SubsetsTest {
    @Test
    public void generateAllSubsetsOfSetOf3ElementsRecursively() {
        Set<Set<Integer>> allSubsets = Subsets.generateRecursively(3);
        assertThat(allSubsets, hasSize(8));
        assertThat(allSubsets, hasItems(
                subSet(),
                subSet(1),
                subSet(2),
                subSet(3),
                subSet(1, 2),
                subSet(1, 3),
                subSet(2, 3),
                subSet(1, 2, 3)
        ));
    }

    @Test
    public void generateAllSubsetsOfSetOf3Elements() {
        Set<Set<Integer>> allSubsets = Subsets.generate(3);
        System.out.println(allSubsets);
        assertThat(allSubsets, hasSize(8));
        assertThat(allSubsets, hasItems(
                subSet(),
                subSet(1),
                subSet(2),
                subSet(3),
                subSet(1, 2),
                subSet(1, 3),
                subSet(2, 3),
                subSet(1, 2, 3)
        ));
    }

    private Set<Integer> subSet(final Integer... elements) {
        return Sets.newHashSet(elements);
    }
}
