package com.carmatech.algo.graphs;

public class UndirectedWeightedGraph extends AbstractWeightedGraph<Edge> implements IWeightedGraph<Edge> {

	public UndirectedWeightedGraph(final int numVertices) {
		super(numVertices);
	}

	@Override
	public void addEdge(final Edge edge) {
		int v = edge.either();
		int w = edge.other(v);
		checkIfCyclic(v, w);
		vertices[v].add(edge);
		vertices[w].add(edge);
	}

	@Override
	public int numEdges() {
		int count = 0;
		for (int v = 0; v < numVertices; ++v)
			count += vertices[v].size();
		return count / 2;
	}

	@Override
	public double averageDegree() {
		return 2.0 * numEdges() / numVertices();
	}

	@Override
	public int numEdgesToSelf() {
		if (!isCyclic)
			return 0;

		int count = 0;
		for (int v = 0; v < numVertices; ++v)
			count += edgesToSelf(v);
		return count / 2;
	}

	@Override
	public String toString() {
		final boolean[] visited = new boolean[numVertices];
		final StringBuilder builder = new StringBuilder();
		for (int v = 0; v < numVertices; v++) {
			for (final Edge edge : neighbours(v)) {
				final int w = (edge.either() == v) ? edge.other(v) : edge.either();
				if (!visited[w]) {
					builder.append(v);
					builder.append(" <-> ");
					builder.append(w);
					builder.append("\n");
				}
			}
			visited[v] = true;
		}
		return builder.toString();
	}
}
