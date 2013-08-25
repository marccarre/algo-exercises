package com.carmatech.algo.datastructures;

public class UndirectedGraph extends AbstractGraph implements IGraph {
	public UndirectedGraph(final int numVertices) {
		super(numVertices);
	}

	@Override
	public void addEdge(final int firstVertice, final int secondVertice) {
		validateVertice(firstVertice);
		validateVertice(secondVertice);
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
		int count = 0;
		for (int v = 0; v < numVertices; ++v)
			count += edgesToSelf(v);
		return count / 2;
	}
}
