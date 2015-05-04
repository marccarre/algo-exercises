package com.carmatech.algo.threading;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SynchronizationTest {
    @Test
    public void sequence() {
        assertThat(Synchronization.sequence(10), is(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
}
