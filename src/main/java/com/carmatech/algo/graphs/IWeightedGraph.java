package com.carmatech.algo.graphs;

import java.util.List;

public interface IWeightedGraph<T extends Edge> {
	void addEdge(T edge);

	List<T> neighbours(int vertice);

	List<T> allEdges();

	int numVertices();

	int numEdges();

	int degree(int vertice);

	int maxDegree();

	double averageDegree();

	int edgesToSelf(int vertice);

	int numEdgesToSelf();

	boolean isCyclic();

	boolean hasEdgeToSelf(int vertice);
}
