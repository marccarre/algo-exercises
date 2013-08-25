package com.carmatech.algo.graphs;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph implements IGraph {
	protected final int numVertices;
	protected final List<Integer>[] vertices;

	public AbstractGraph(final int numVertices) {
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

	protected void validateVertice(final int vertice) {
		checkArgument(isExistingVertice(vertice), "Vertice must be between 0 and " + (numVertices - 1) + " (included), but was [" + vertice + "].");
	}

	protected boolean isExistingVertice(final int vertice) {
		return (0 <= vertice) && (vertice < numVertices);
	}

	@Override
	public abstract void addEdge(final int firstVertice, final int secondVertice);

	@Override
	public List<Integer> neighbours(final int vertice) {
		validateVertice(vertice);
		return Collections.unmodifiableList(vertices[vertice]);
	}

	@Override
	public int numVertices() {
		return numVertices;
	}

	@Override
	public abstract int numEdges();

	@Override
	public int degree(final int vertice) {
		validateVertice(vertice);
		return vertices[vertice].size();
	}

	@Override
	public int maxDegree() {
		int maxDegree = Integer.MIN_VALUE;

		for (int v = 0; v < numVertices; ++v) {
			final int degree = degree(v);

			if (degree > maxDegree)
				maxDegree = degree;
		}

		return maxDegree;
	}

	@Override
	public abstract double averageDegree();

	@Override
	public int edgesToSelf(final int vertice) {
		validateVertice(vertice);

		final List<Integer> neighbours = neighbours(vertice);
		int count = 0;
		for (final int neighbour : neighbours)
			if (neighbour == vertice)
				++count;

		return count;
	}

	@Override
	public abstract int numEdgesToSelf();
}