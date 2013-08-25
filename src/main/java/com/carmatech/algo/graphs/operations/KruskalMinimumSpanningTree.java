package com.carmatech.algo.graphs.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.carmatech.algo.graphs.Edge;
import com.carmatech.algo.graphs.IWeightedGraph;

public class KruskalMinimumSpanningTree {
	private final IWeightedGraph<Edge> graph;
	private final List<Edge> mst = new LinkedList<Edge>();
	private final boolean[] visited;
	private final Queue<Edge> edges = new PriorityQueue<Edge>();

	public KruskalMinimumSpanningTree(final IWeightedGraph<Edge> graph) {
		this.graph = graph;
		this.visited = new boolean[graph.numVertices()];
	}

	public void findMinimumSpanningTree() {
		mst.clear();
		edges.clear();

		// TODO
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
