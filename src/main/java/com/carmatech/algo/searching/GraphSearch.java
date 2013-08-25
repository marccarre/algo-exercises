package com.carmatech.algo.searching;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.carmatech.algo.datastructures.IGraph;

public final class GraphSearch {
	private static final int ROOT = -1;
	private final IGraph graph;
	private final int rootVertice;

	private final boolean[] visited;
	private final int[] pathTo;

	public GraphSearch(final IGraph graph, final int rootVertice) {
		this.graph = graph;
		this.rootVertice = rootVertice;
		visited = new boolean[graph.numVertices()];
		pathTo = new int[graph.numVertices()];
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

	public int[] depthFirstSearch() {
		initialize(visited);
		initialize(pathTo);
		depthFirstSearch(rootVertice, ROOT);
		return pathTo;
	}

	private void depthFirstSearch(final int vertice, final int previousVertice) {
		if (visited[vertice])
			return;

		visited[vertice] = true;
		pathTo[vertice] = previousVertice;

		final List<Integer> neighbours = graph.adjacentNodes(vertice);
		for (final int neighbour : neighbours)
			if (!visited[neighbour])
				depthFirstSearch(neighbour, vertice);
	}

	public int[] breadthFirstSearch() {
		initialize(visited);
		initialize(pathTo);

		final Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(rootVertice);
		visited[rootVertice] = true;
		pathTo[rootVertice] = ROOT;

		while (!queue.isEmpty()) {
			final int vertice = queue.poll();

			final List<Integer> neighbours = graph.adjacentNodes(vertice);
			for (final int neighbour : neighbours) {
				if (!visited[neighbour]) {
					visited[neighbour] = true;
					pathTo[neighbour] = vertice;
					queue.add(neighbour);
				}
			}
		}
		return pathTo;
	}

	public List<Integer> pathTo(final int vertice) {
		final LinkedList<Integer> path = new LinkedList<Integer>();
		if (!visited[vertice])
			return path;

		for (int v = vertice; v != rootVertice; v = pathTo[v])
			path.push(v);
		path.push(rootVertice);
		return path;
	}
}
