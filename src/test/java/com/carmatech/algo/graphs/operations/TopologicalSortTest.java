package com.carmatech.algo.graphs.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.carmatech.algo.graphs.DirectedGraph;

public class TopologicalSortTest {
	private DirectedGraph createDirectedGraph() {
		final DirectedGraph graph = new DirectedGraph(7);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(1, 4);
		graph.addEdge(3, 2);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);
		graph.addEdge(5, 2);
		graph.addEdge(6, 0);
		graph.addEdge(6, 4);
		return graph;
	}

	@Test
	public void runTopologicalSort() {
		DirectedGraph graph = createDirectedGraph();
		TopologicalSort sort = new TopologicalSort(graph);

		List<Integer> goldenPath = sort.run();
		List<Integer> expectedPath = Arrays.asList(3, 6, 0, 5, 2, 1, 4);
		assertThat(goldenPath, equalTo(expectedPath));
	}
}
