package com.carmatech.algo.unionfind;

public interface IUnionFind {

	void union(int p, int q);

	boolean connected(int p, int q);

	int[] components();
}