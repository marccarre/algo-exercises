package com.carmatech.algo.dynprog;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RodTest {
    @Test
    public void maxPrice() {
        Rod rod = new Rod(new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30});
        assertThat(rod.maxPrice(0), is(0));
        assertThat(rod.maxPrice(1), is(1));
        assertThat(rod.maxPrice(2), is(5));
        assertThat(rod.maxPrice(3), is(8));
        assertThat(rod.maxPrice(4), is(10));
        assertThat(rod.maxPrice(5), is(13));
        assertThat(rod.maxPrice(6), is(17));
        assertThat(rod.maxPrice(7), is(18));
        assertThat(rod.maxPrice(8), is(22));
        assertThat(rod.maxPrice(9), is(25));
        assertThat(rod.maxPrice(10), is(30));
    }

    @Test
    public void cutForMaxPrice() {
        Rod rod = new Rod(new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30});
        assertThat(rod.cutForMaxPrice(0), is(list()));
        assertThat(rod.cutForMaxPrice(1), is(list(1)));
        assertThat(rod.cutForMaxPrice(2), is(list(2)));
        assertThat(rod.cutForMaxPrice(3), is(list(3)));
        assertThat(rod.cutForMaxPrice(4), is(list(2, 2)));
        assertThat(rod.cutForMaxPrice(5), is(list(2, 3)));
        assertThat(rod.cutForMaxPrice(6), is(list(6)));
        assertThat(rod.cutForMaxPrice(7), is(list(1, 6)));
        assertThat(rod.cutForMaxPrice(8), is(list(2, 6)));
        assertThat(rod.cutForMaxPrice(9), is(list(3, 6)));
        assertThat(rod.cutForMaxPrice(10), is(list(10)));
    }

    private List<Integer> list(final int... integers) {
        final List<Integer> list = Lists.newArrayList();
        for (final int integer : integers)
            list.add(integer);
        return list;
    }
}
