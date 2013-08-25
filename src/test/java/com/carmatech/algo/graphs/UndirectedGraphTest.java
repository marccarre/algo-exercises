package com.carmatech.algo.graphs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.carmatech.algo.graphs.IGraph;
import com.carmatech.algo.graphs.UndirectedGraph;

public class UndirectedGraphTest {
	private static final double TOLERANCE_ON_DOUBLES = 0.00000001;
	private static final int GRAPH_SIZE = 6;
	final IGraph graph = new UndirectedGraph(GRAPH_SIZE);

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
	public void numberOfVertices() {
		assertThat(graph.numVertices(), is(6));
	}

	@Test
	public void numberOfEdges() {
		assertThat(graph.numEdges(), is(7));
	}

	@Test
	public void degree() {
		assertThat(graph.degree(0), is(3));
	}

	@Test
	public void maxDegree() {
		assertThat(graph.maxDegree(), is(4));
	}

	@Test
	public void averageDegree() {
		assertThat(graph.averageDegree(), is(closeTo(2.3333333333, TOLERANCE_ON_DOUBLES)));
	}
}
