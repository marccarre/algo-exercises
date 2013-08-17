package com.carmatech.algo.searching;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.carmatech.algo.datastructures.Graph;

public class GraphSearchingTest {
	private static final int GRAPH_SIZE = 6;
	final Graph graph = new Graph(GRAPH_SIZE);

	@Before
	public void setUp() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
	}

	@Test
	public void depthFirstSearch() {
		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.depthFirstSearch();
		for (int i = 0; i < GRAPH_SIZE; ++i)
			System.out.println("Path to " + i + " is " + graphSearch.pathTo(i));

		assertThat(paths, equalTo(new int[] { -1, 0, 1, 2, 2, 3 }));
	}

	@Test
	public void breadthFirstSearch() {
		GraphSearch graphSearch = new GraphSearch(graph, 0);
		int[] paths = graphSearch.breadthFirstSearch();
		for (int i = 0; i < GRAPH_SIZE; ++i)
			System.out.println("Path to " + i + " is " + graphSearch.pathTo(i));

		assertThat(paths, equalTo(new int[] { -1, 0, 0, 2, 2, 0 }));
	}
}
