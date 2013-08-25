package com.carmatech.algo.graphs.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.carmatech.algo.graphs.IGraph;
import com.carmatech.algo.graphs.UndirectedGraph;

public class GraphConnectedComponentsTest {
	private static final int GRAPH_SIZE = 13;
	final IGraph graph = new UndirectedGraph(GRAPH_SIZE);

	@Before
	public void setUp() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 5);
		graph.addEdge(0, 6);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(7, 8);
		graph.addEdge(9, 10);
		graph.addEdge(9, 11);
		graph.addEdge(9, 12);
		graph.addEdge(11, 12);
	}

	@Test
	public void computeConnectedComponents() {
		GraphConnectedComponents cc = new GraphConnectedComponents(graph);
		cc.computeConnectedComponents();

		assertThat(cc.numConnectedComponents(), is(3));

		assertThat(cc.areConnected(0, 3), is(true));
		assertThat(cc.component(3), is(0));
		assertThat(cc.areConnected(0, 8), is(false));
		assertThat(cc.areConnected(0, 12), is(false));

		assertThat(cc.areConnected(7, 8), is(true));
		assertThat(cc.component(8), is(1));

		assertThat(cc.areConnected(10, 12), is(true));
		assertThat(cc.component(12), is(2));
	}
}
