package com.carmatech.algo.trees;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.carmatech.algo.trees.BinomialTree;

public class BinomialTreeTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void createValidBinomialTrees() {
		BinomialTree<Integer> tree1 = new BinomialTree<Integer>(new Integer[] { 1 });
		assertThat(tree1.depth(), is(1));
		assertThat(tree1.numberOfPaths(), is(1));

		BinomialTree<Integer> tree2 = new BinomialTree<Integer>(new Integer[] { 1, 2, 3 });
		assertThat(tree2.depth(), is(2));
		assertThat(tree2.numberOfPaths(), is(2));

		BinomialTree<Integer> tree3 = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6 });
		assertThat(tree3.depth(), is(3));
		assertThat(tree3.numberOfPaths(), is(4));

		BinomialTree<Integer> tree4 = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		assertThat(tree4.depth(), is(4));
		assertThat(tree4.numberOfPaths(), is(8));

		BinomialTree<Integer> tree5 = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
		assertThat(tree5.depth(), is(5));
		assertThat(tree5.numberOfPaths(), is(16));
	}

	@Test
	public void createInvalidBinomialTrees() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage(is("Provided array doesn't define a binomial tree: Expected size for a binomial tree: [6]. Actual size: [5]."));
		new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5 });
	}

	@Test
	public void generatePaths() {
		BinomialTree<Integer> tree = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		int[][] paths = tree.paths();

		printPaths(paths);

		assertThat(paths.length, is(tree.numberOfPaths()));
		assertThat(paths, equalTo(new int[][] { new int[] { 0, 1, 3, 6 }, new int[] { 0, 1, 3, 7 }, new int[] { 0, 1, 4, 7 }, new int[] { 0, 1, 4, 8 },
				new int[] { 0, 2, 4, 7 }, new int[] { 0, 2, 4, 8 }, new int[] { 0, 2, 5, 8 }, new int[] { 0, 2, 5, 9 } }));
	}

	private void printPaths(int[][] paths) {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths[0].length; j++) {
				System.out.print(paths[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	@Test
	public void findLargestPath() {
		BinomialTree<Integer> tree = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		assertThat(tree.largestPath(), equalTo(new int[] { 0, 2, 5, 9 }));
	}

	@Test
	public void findLargestPathValue() {
		BinomialTree<Integer> tree = new BinomialTree<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		assertThat(tree.largestPathValue(), is(Long.valueOf(1 + 3 + 6 + 10)));
	}
}
