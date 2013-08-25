package com.carmatech.algo.graphs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class DirectedGraphTest {
	private static final double TOLERANCE_ON_DOUBLES = 0.00000001;
	private static final int GRAPH_SIZE = 6;
	final IGraph graph = new DirectedGraph(GRAPH_SIZE);

	@Before
	public void setUp() {
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 4);
		graph.addEdge(3, 2);
		graph.addEdge(3, 5);
		graph.addEdge(4, 3);
		graph.addEdge(5, 0);
	}

	@Test
	public void graphToString() {
		assertThat(graph.toString(), is("0 -> 1\n0 -> 2\n1 -> 2\n2 -> 4\n3 -> 2\n3 -> 5\n4 -> 3\n5 -> 0\n"));
	}

	@Test
	public void numberOfVertices() {
		assertThat(graph.numVertices(), is(6));
	}

	@Test
	public void numberOfEdges() {
		assertThat(graph.numEdges(), is(8));
	}

	@Test
	public void degree() {
		assertThat(graph.degree(0), is(2));
	}

	@Test
	public void maxDegree() {
		assertThat(graph.maxDegree(), is(2));
	}

	@Test
	public void averageDegree() {
		assertThat(graph.averageDegree(), is(closeTo(8.0 / 6, TOLERANCE_ON_DOUBLES)));
	}
}
