package com.carmatech.algo.graphs;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWeightedGraph<T extends Edge> implements IWeightedGraph<T> {
	protected final int numVertices;
	protected final List<T>[] vertices;
	protected boolean isCyclic = false;

	public AbstractWeightedGraph(final int numVertices) {
		checkArgument(numVertices > 0, "Number of vertices in a graph must be strictly positive, but was [" + numVertices + "].");
		this.numVertices = numVertices;
		vertices = initializeVerticesWithNoEdge(numVertices);
	}

	@SuppressWarnings("unchecked")
	private List<T>[] initializeVerticesWithNoEdge(final int numVertices) {
		final List<T>[] vertices = (List<T>[]) new List<?>[numVertices];
		for (int i = 0; i < numVertices; ++i)
			vertices[i] = new LinkedList<T>();
		return vertices;
	}

	protected void validateVertice(final int vertice) {
		checkArgument(isExistingVertice(vertice), "Vertice must be between 0 and " + (numVertices - 1) + " (included), but was [" + vertice + "].");
	}

	protected boolean isExistingVertice(final int vertice) {
		return (0 <= vertice) && (vertice < numVertices);
	}

	@Override
	/**
	 * N.B. call {@code checkIfCyclic} when implementing {@code addEdge} in subclasses.
	 */
	public abstract void addEdge(final Edge edge);

	protected void checkIfCyclic(final int firstVertice, final int secondVertice) {
		if (firstVertice == secondVertice)
			isCyclic = true;
	}

	@Override
	public List<T> neighbours(final int vertice) {
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

		if (!isCyclic)
			return 0;

		final List<T> neighbours = neighbours(vertice);
		int count = 0;
		for (final T neighbour : neighbours) {
			int v = neighbour.either();
			int w = neighbour.other(v);
			if ((v == vertice) || (w == vertice))
				++count;

		}

		return count;
	}

	@Override
	public abstract int numEdgesToSelf();

	@Override
	public boolean isCyclic() {
		return isCyclic;
	}

	@Override
	public boolean hasEdgeToSelf(final int vertice) {
		validateVertice(vertice);

		if (!isCyclic)
			return false;

		final List<T> neighbours = neighbours(vertice);
		for (final T neighbour : neighbours) {
			int v = neighbour.either();
			int w = neighbour.other(v);
			if ((v == vertice) || (w == vertice))
				return true;
		}

		return false;
	}
}
