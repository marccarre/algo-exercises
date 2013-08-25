package com.carmatech.algo.graphs.operations;

import com.carmatech.algo.graphs.IGraph;

public class UndirectedGraphConnectedComponents {
	private final IGraph graph;

	private int numComponents;
	private final boolean[] visited;
	private final int[] components;

	public UndirectedGraphConnectedComponents(final IGraph graph) {
		this.graph = graph;
		visited = new boolean[graph.numVertices()];
		components = new int[graph.numVertices()];
	}

	private static void initialize(final boolean[] array) {
		final int length = array.length;
		for (int i = 0; i < length; ++i)
			array[i] = false;
	}

	private static void initialize(final int[] array) {
		final int length = array.length;
		for (int i = 0; i < length; ++i)
			array[i] = 0;
	}

	private void reInitialize() {
		initialize(visited);
		initialize(components);
		numComponents = 0;
	}

	public void computeConnectedComponents() {
		reInitialize();

		for (int v = 0; v < graph.numVertices(); ++v) {
			if (!visited[v]) {
				depthFirstSearch(v);
				numComponents++;
			}
		}
	}

	private void depthFirstSearch(final int v) {
		if (visited[v])
			return;

		visited[v] = true;
		components[v] = numComponents;

		for (final int neighbour : graph.adjacentNodes(v))
			depthFirstSearch(neighbour);
	}

	public int numConnectedComponents() {
		return numComponents;
	}

	public boolean areConnected(final int v, final int w) {
		return (components[v] == components[w]);
	}

	public int component(final int v) {
		return components[v];
	}
}
