package com.carmatech.algo.graphs;

import java.util.List;

public interface IGraph {
	void addEdge(int firstVertice, int secondVertice);

	List<Integer> adjacentNodes(int vertice);

	int numVertices();

	int numEdges();

	int degree(int vertice);

	int maxDegree();

	double averageDegree();

	int edgesToSelf(int vertice);

	int numEdgesToSelf();
}