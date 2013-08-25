package com.carmatech.algo.unionfind;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class QuickFindTest {
	@Test
	public void quickFind() {
		QuickFind qf = new QuickFind(10);
		qf.union(0, 5);
		qf.union(5, 6);
		qf.union(6, 1);
		qf.union(1, 2);
		qf.union(2, 7);
		qf.union(8, 3);
		qf.union(3, 4);
		qf.union(4, 9);

		assertThat(qf.connected(0, 7), is(true));
		assertThat(qf.connected(8, 9), is(true));
		assertThat(qf.connected(7, 8), is(false));

		assertThat(qf.components(), is(new int[] { 7, 7, 7, 9, 9, 7, 7, 7, 9, 9 }));
	}
}
