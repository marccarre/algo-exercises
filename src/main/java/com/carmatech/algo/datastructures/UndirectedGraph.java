package com.carmatech.algo.datastructures;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private final int numVertices;
	private final List<Integer>[] vertices;

	public Graph(final int numVertices) {
		checkArgument(numVertices > 0, "Number of vertices in a graph must be strictly positive, but was [" + numVertices + "].");
		this.numVertices = numVertices;
		vertices = initializeVerticesWithNoEdge(numVertices);
	}

	@SuppressWarnings("unchecked")
	private List<Integer>[] initializeVerticesWithNoEdge(final int numVertices) {
		final List<Integer>[] vertices = (List<Integer>[]) new List<?>[numVertices];
		for (int i = 0; i < numVertices; ++i)
			vertices[i] = new LinkedList<Integer>();
		return vertices;
	}

	public void addEdge(final int firstVertice, final int secondVertice) {
		validateVertice(firstVertice);
		validateVertice(secondVertice);
		vertices[firstVertice].add(secondVertice);
		vertices[secondVertice].add(firstVertice);
	}

	public List<Integer> adjacentNodes(final int vertice) {
		validateVertice(vertice);
		return Collections.unmodifiableList(vertices[vertice]);
	}

	private void validateVertice(final int vertice) {
		checkArgument(isExistingVertice(vertice), "Vertice must be between 0 and " + (numVertices - 1) + " (included), but was [" + vertice + "].");
	}

	private boolean isExistingVertice(final int vertice) {
		return (0 <= vertice) && (vertice < numVertices);
	}

	public int numVertices() {
		return numVertices;
	}
}
