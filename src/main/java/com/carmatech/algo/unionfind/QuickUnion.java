package com.carmatech.algo.unionfind;

public class QuickUnion extends AbstractUnionFind implements IUnionFind {
	private final int[] size;

	public QuickUnion(final int n) {
		super(n);
		size = new int[n];
	}

	@Override
	public void union(final int p, final int q) {
		validate(p);
		validate(q);
		final int proot = root(p);
		final int qroot = root(q);

		if (size[proot] < size[qroot]) {
			id[proot] = qroot;
			size[qroot] += size[proot];
		} else {
			id[qroot] = proot;
			size[proot] += size[qroot];
		}
	}

	@Override
	public boolean connected(final int p, final int q) {
		validate(p);
		validate(q);
		return root(p) == root(q);
	}

	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
}
