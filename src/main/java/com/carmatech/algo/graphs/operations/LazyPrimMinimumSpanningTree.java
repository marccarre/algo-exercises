package com.carmatech.algo.graphs.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.carmatech.algo.graphs.Edge;
import com.carmatech.algo.graphs.IWeightedGraph;

public class LazyPrimMinimumSpanningTree {
	private final IWeightedGraph<Edge> graph;
	private final List<Edge> mst = new LinkedList<Edge>();
	private final boolean[] visited;
	private final Queue<Edge> edges = new PriorityQueue<Edge>();

	public LazyPrimMinimumSpanningTree(final IWeightedGraph<Edge> graph) {
		this.graph = graph;
		this.visited = new boolean[graph.numVertices()];
	}

	public void findMinimumSpanningTree() {
		mst.clear();
		edges.clear();

		visit(0);

		while (!edges.isEmpty() && (mst.size() < graph.numVertices() - 1)) {
			final Edge edge = edges.remove();
			int v = edge.either();
			int w = edge.other(v);

			if (visited[v] && visited[w])
				continue;

			mst.add(edge);
			if (!visited[v])
				visit(v);
			if (!visited[w])
				visit(w);
		}
	}

	private void visit(final int v) {
		visited[v] = true;
		for (final Edge edge : graph.neighbours(v))
			if (!visited[edge.other(v)])
				edges.add(edge);
	}

	public List<Edge> edges() {
		return mst;
	}

	public int size() {
		return mst.size();
	}
}
