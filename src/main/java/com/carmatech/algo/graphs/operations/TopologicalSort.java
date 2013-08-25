package com.carmatech.algo.graphs.operations;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.carmatech.algo.graphs.DirectedGraph;

public class TopologicalSort {
	private final DirectedGraph graph;
	private final int numVertices;
	private final LinkedList<Integer> goldenPath = new LinkedList<Integer>();
	private final boolean[] visited;

	public TopologicalSort(final DirectedGraph graph) {
		checkNotNull(graph, "Provided directed graph must NOT be null.");
		checkArgument(!graph.isCyclic(), "Topological sort does NOT work on cyclic directed graphs.");
		this.graph = graph;

		numVertices = graph.numVertices();
		visited = new boolean[numVertices];
	}

	private static void initialize(final boolean[] array) {
		final int length = array.length;
		for (int i = 0; i < length; ++i)
			array[i] = false;
	}

	private void reInitialize() {
		goldenPath.clear();
		initialize(visited);
	}

	public List<Integer> run() {
		reInitialize();

		for (int v = 0; v < numVertices; ++v)
			if (!visited[v])
				depthFirstSearch(v);

		return Collections.unmodifiableList(goldenPath);
	}

	private void depthFirstSearch(final int v) {
		visited[v] = true;

		for (final int neighbour : graph.neighbours(v))
			if (!visited[neighbour])
				depthFirstSearch(neighbour);

		goldenPath.push(v);
	}
}
