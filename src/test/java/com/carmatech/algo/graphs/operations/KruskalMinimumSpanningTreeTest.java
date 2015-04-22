package com.carmatech.algo.graphs.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.carmatech.algo.graphs.Edge;
import com.carmatech.algo.graphs.UndirectedWeightedGraph;

public class KruskalMinimumSpanningTreeTest {
	private final List<Edge> expectedMST = Arrays.asList(
			new Edge(0, 7, 0.16),
			new Edge(2, 3, 0.17),
			new Edge(1, 7, 0.19),
			new Edge(0, 2, 0.26),
			new Edge(5, 7, 0.28),
			new Edge(4, 5, 0.35),
			new Edge(6, 2, 0.40)
	);

	private final UndirectedWeightedGraph graph = createUndirectedWeightedGraph();
	private UndirectedWeightedGraph createUndirectedWeightedGraph() {
		final UndirectedWeightedGraph graph = new UndirectedWeightedGraph(8);
		graph.addEdge(new Edge(0, 7, 0.16));
		graph.addEdge(new Edge(2, 3, 0.17));
		graph.addEdge(new Edge(1, 7, 0.19));
		graph.addEdge(new Edge(0, 2, 0.26));
		graph.addEdge(new Edge(5, 7, 0.28));
		graph.addEdge(new Edge(1, 2, 0.29));
		graph.addEdge(new Edge(1, 5, 0.32));
		graph.addEdge(new Edge(2, 7, 0.34));
		graph.addEdge(new Edge(4, 5, 0.35));
		graph.addEdge(new Edge(1, 2, 0.36));
		graph.addEdge(new Edge(4, 7, 0.37));
		graph.addEdge(new Edge(0, 4, 0.38));
		graph.addEdge(new Edge(6, 2, 0.40));
		graph.addEdge(new Edge(3, 6, 0.52));
		graph.addEdge(new Edge(6, 0, 0.58));
		graph.addEdge(new Edge(6, 4, 0.93));
		return graph;
	}

	@Test
	public void findMinimumSpanningTree() {
		KruskalMinimumSpanningTree kruskal = new KruskalMinimumSpanningTree(graph);
		kruskal.findMinimumSpanningTree();

		List<Edge> mst = kruskal.edges();
		assertThat(mst, is(expectedMST));
	}

	@Test
	public void applyKruskalVisitorToCreateMinimumSpanningTree() {
		List<Edge> mst = graph.visit(new MinimumSpanningTreeVisitor());
		assertThat(mst, is(expectedMST));

	}
}
