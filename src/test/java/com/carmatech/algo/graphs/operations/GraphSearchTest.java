package com.carmatech.algo.graphs.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.carmatech.algo.graphs.DirectedGraph;
import com.carmatech.algo.graphs.UndirectedGraph;

public class GraphSearchTest {

	private UndirectedGraph createUndirectedGraph() {
		final UndirectedGraph graph = new UndirectedGraph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		return graph;
	}

	private DirectedGraph createDirectedGraph() {
		final DirectedGraph graph = new DirectedGraph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 2);
		graph.addEdge(3, 5);
		graph.addEdge(4, 3);
		graph.addEdge(5, 0);
		return graph;
	}

	@Test
	public void depthFirstSearchOnUndirectedGraph() {
		UndirectedGraph graph = createUndirectedGraph();

		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.depthFirstSearch();
		graphSearch.printPaths();

		assertThat(paths, equalTo(new int[] { -1, 0, 1, 2, 2, 3 }));
	}

	@Test
	public void breadthFirstSearchOnUndirectedGraph() {
		UndirectedGraph graph = createUndirectedGraph();

		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.breadthFirstSearch();
		graphSearch.printPaths();

		assertThat(paths, equalTo(new int[] { -1, 0, 0, 2, 2, 0 }));
	}

	@Test
	public void depthFirstSearchOnDirectedGraph() {
		DirectedGraph graph = createDirectedGraph();

		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.depthFirstSearch();
		graphSearch.printPaths();

		assertThat(paths, equalTo(new int[] { -1, 0, 1, 4, 2, 3 }));
	}

	@Test
	public void breadthFirstSearchOnDirectedGraph() {
		DirectedGraph graph = createDirectedGraph();

		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.breadthFirstSearch();
		graphSearch.printPaths();

		assertThat(paths, equalTo(new int[] { -1, 0, 0, 4, 2, 3 }));
	}
}
