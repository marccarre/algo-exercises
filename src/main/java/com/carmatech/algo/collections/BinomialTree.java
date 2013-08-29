package com.carmatech.algo.collections;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinomialTree<T extends Comparable<T>> {
	private final T[] tree;
	private final int depth;
	private final int numberOfPaths;

	/**
	 * @param tree
	 *            array defining the binomial tree for the top-left (root) to the bottom-right element.
	 */
	public BinomialTree(final T[] tree) {
		isValidTree(tree);
		this.tree = tree;
		this.depth = calculateDepth();
		this.numberOfPaths = calculateNumberOfPaths();
	}

	private void isValidTree(final T[] tree) {
		long sum = 0L;
		long i = 1L;
		while (sum < tree.length)
			sum += i++;

		if (sum != tree.length) {
			final String cause = "Expected size for a binomial tree: [" + sum + "]. Actual size: [" + tree.length + "].";
			throw new IllegalArgumentException("Provided array doesn't define a binomial tree: " + cause);
		}
	}

	private int calculateDepth() {
		return (int) (-1 + Math.sqrt(1 + (8 * tree.length))) / 2;
	}

	private int calculateNumberOfPaths() {
		return (int) Math.pow(2, depth - 1);
	}

	public int depth() {
		return depth;
	}

	public int numberOfPaths() {
		return numberOfPaths;
	}

	/**
	 * Generates all the possible paths in the binomial tree.
	 * 
	 * @return indexes in the provided array corresponding to the paths. 1st dimension = number of paths. 2nd dimension = depth of the tree/path.
	 */
	public int[][] paths() {
		final List<int[]> allPaths = new LinkedList<int[]>();

		visit(allPaths, initializePath(), 1, 0, 1);
		visit(allPaths, initializePath(), 1, 0, 2);

		return allPaths.toArray(new int[allPaths.size()][depth]);
	}

	private int[] initializePath() {
		final int[] path = new int[depth];
		path[0] = 0;
		return path;
	}

	private void visit(final List<int[]> allPaths, final int[] path, final int currentDepth, final int parent, final int child) {
		path[currentDepth] = child;

		final int leftChild = child + currentDepth + 1;
		final int rightChild = child + currentDepth + 2;

		if (leftChild < tree.length) {
			final int nextDepth = currentDepth + 1;
			visit(allPaths, path, nextDepth, child, leftChild);
			visit(allPaths, copy(path), nextDepth, child, rightChild);
		} else {
			allPaths.add(path);
		}
	}

	public long pathLargestValueWithoutPathConstruction() {
		return pathLargestValueWithoutPathConstruction(0, 0, toInt(tree[0]));
	}

	private long pathLargestValueWithoutPathConstruction(final int parent, final int depth, final long sum) {
		final int leftChild = parent + depth + 1;
		final int rightChild = parent + depth + 2;

		if (!(leftChild < tree.length))
			return sum;

		final long leftSum = pathLargestValueWithoutPathConstruction(leftChild, depth + 1, sum + toInt(tree[leftChild]));
		final long rightSum = pathLargestValueWithoutPathConstruction(rightChild, depth + 1, sum + toInt(tree[rightChild]));
		return Math.max(leftSum, rightSum);
	}

	public long pathLargestValueWithoutPathConstructionNorRecursions() {
		final Deque<Step> stack = new LinkedList<Step>();

		long sum = Long.MIN_VALUE;
		stack.push(new Step(0, 0, toLong(tree[0])));

		while (!stack.isEmpty()) {
			final Step step = stack.pop();

			int depth = step.depth;
			final int leftChild = step.index + depth + 1;
			final int rightChild = step.index + depth + 2;

			if (leftChild < tree.length) {
				stack.push(new Step(leftChild, depth + 1, step.sum + toLong(tree[leftChild])));
				stack.push(new Step(rightChild, depth + 1, step.sum + toLong(tree[rightChild])));
			} else {
				sum = Math.max(sum, step.sum);
			}
		}
		return sum;
	}

	private static class Step {
		private final int index;
		private final int depth;
		private final long sum;

		private Step(final int index, final int depth, final long sum) {
			this.index = index;
			this.depth = depth;
			this.sum = sum;
		}
	}

	private int toInt(final T value) {
		return Integer.parseInt(value.toString());
	}

	private long toLong(final T value) {
		return Long.parseLong(value.toString());
	}

	private int[] copy(final int[] path) {
		final int[] copy = new int[path.length];
		System.arraycopy(path, 0, copy, 0, path.length);
		return copy;
	}

	public int[] largestPath() {
		final int[][] paths = paths();

		long max = Long.MIN_VALUE;
		int indexMax = -1;

		for (int i = 0; i < numberOfPaths; i++) {
			final long sum = sum(paths, i);
			if (sum > max) {
				max = sum;
				indexMax = i;
			}
		}

		return paths[indexMax];
	}

	public long largestPathValue() {
		final int[][] paths = paths();

		long max = Long.MIN_VALUE;

		for (int i = 0; i < numberOfPaths; i++) {
			final long sum = sum(paths, i);
			if (sum > max) {
				max = sum;
			}
		}

		return max;
	}

	private long sum(final int[][] paths, final int i) {
		long sum = 0L;
		for (int j = 0; j < depth; j++) {
			final int index = paths[i][j];
			// TODO: check if T is "summable" before running largestPath and largestPathValue, to avoid the below nasty cast:
			sum += (Integer) tree[index];
		}
		return sum;
	}
}
