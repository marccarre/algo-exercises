package com.carmatech.algo.unionfind;

public class QuickFind extends AbstractUnionFind implements IUnionFind {
	public QuickFind(final int n) {
		super(n);
	}

	@Override
	public void union(final int p, final int q) {
		validate(p);
		validate(q);
		final int pid = id[p];
		final int qid = id[q];

		for (int i = 0; i < n; ++i)
			if (id[i] == pid)
				id[i] = qid;
	}

	@Override
	public boolean connected(final int p, final int q) {
		validate(p);
		validate(q);
		return id[p] == id[q];
	}
}
