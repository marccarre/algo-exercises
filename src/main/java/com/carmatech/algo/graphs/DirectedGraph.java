package com.carmatech.algo.graphs;

public class DirectedGraph extends AbstractGraph implements IGraph {
	public DirectedGraph(final int numVertices) {
		super(numVertices);
	}

	@Override
	public void addEdge(final int from, final int to) {
		validateVertice(from);
		validateVertice(to);
		vertices[from].add(to);
	}

	@Override
	public int numEdges() {
		int count = 0;
		for (int v = 0; v < numVertices; ++v)
			count += vertices[v].size();
		return count;
	}

	@Override
	/**
	 * N.B.: OUT degree.
	 */
	public int degree(final int vertice) {
		return super.degree(vertice);
	}

	@Override
	/**
	 * N.B.: max OUT degree.
	 */
	public int maxDegree() {
		return super.maxDegree();
	}

	@Override
	/**
	 * N.B.: average OUT degree.
	 */
	public double averageDegree() {
		return ((double) numEdges()) / numVertices();
	}

	@Override
	public int numEdgesToSelf() {
		int count = 0;
		for (int v = 0; v < numVertices; ++v)
			count += edgesToSelf(v);
		return count;
	}

	public DirectedGraph reverse() {
		throw new UnsupportedOperationException("Coming soon! (Stay tuned or... just contribute!)");
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (int v = 0; v < numVertices; v++) {
			for (final int w : neighbours(v)) {
				builder.append(v);
				builder.append(" -> ");
				builder.append(w);
				builder.append("\n");
			}
		}
		return builder.toString();
	}
}
