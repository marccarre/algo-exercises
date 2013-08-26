package com.carmatech.algo.graphs.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.carmatech.algo.graphs.Edge;
import com.carmatech.algo.graphs.IWeightedGraph;
import com.carmatech.algo.unionfind.IUnionFind;
import com.carmatech.algo.unionfind.QuickUnion;

public class KruskalMinimumSpanningTree {
	private final IWeightedGraph<Edge> graph;
	private final int numVertices;
	private final List<Edge> mst = new LinkedList<Edge>();
	private final Queue<Edge> edges = new PriorityQueue<Edge>();
	private final IUnionFind unionFind;

	public KruskalMinimumSpanningTree(final IWeightedGraph<Edge> graph) {
		this.graph = graph;
		numVertices = graph.numVertices();
		this.unionFind = new QuickUnion(numVertices); // O(V)
	}

	/**
	 * Find the minimum spanning tree in O(E.log(E)).
	 */
	public void findMinimumSpanningTree() {
		mst.clear();
		edges.clear();

		edges.addAll(graph.allEdges()); // O(E.log(E))

		while (!edges.isEmpty() && (mst.size() < numVertices - 1)) { // V-1 times best case, E times worst case
			final Edge edge = edges.remove(); // O(log(E))
			final int v = edge.either();
			final int w = edge.other(v);

			if (unionFind.connected(v, w)) // O(1)
				continue;

			// V-1 times, thanks to above condition:
			mst.add(edge); // O(1)
			unionFind.union(v, w); // O(V.log(V))
		}
	}

	public List<Edge> edges() {
		return mst;
	}

	public int size() {
		return mst.size();
	}
}
