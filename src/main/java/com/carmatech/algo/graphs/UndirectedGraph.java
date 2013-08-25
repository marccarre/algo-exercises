package com.carmatech.algo.graphs;

public class UndirectedGraph extends AbstractGraph implements IGraph {
	public UndirectedGraph(final int numVertices) {
		super(numVertices);
	}

	@Override
	public void addEdge(final int firstVertice, final int secondVertice) {
		validateVertice(firstVertice);
		validateVertice(secondVertice);
		checkIfCyclic(firstVertice, secondVertice);
		vertices[firstVertice].add(secondVertice);
		vertices[secondVertice].add(firstVertice);
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
			for (final int w : neighbours(v)) {
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
