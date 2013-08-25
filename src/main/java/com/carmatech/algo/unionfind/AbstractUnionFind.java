package com.carmatech.algo.unionfind;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractUnionFind implements IUnionFind {
	protected final int n;
	protected final int[] id;

	public AbstractUnionFind(final int n) {
		this.n = n;
		this.id = new int[n];
		for (int i = 0; i < n; ++i)
			id[i] = i;
	}

	protected void validate(final int p) {
		checkArgument(0 <= p && p < n, "Item must be between 0 (inclusive) and " + n + " (exclusive) but was [" + p + "].");
	}

	@Override
	public abstract void union(final int p, final int q);

	@Override
	public abstract boolean connected(final int p, final int q);

	@Override
	public int[] components() {
		return id;
	}
}