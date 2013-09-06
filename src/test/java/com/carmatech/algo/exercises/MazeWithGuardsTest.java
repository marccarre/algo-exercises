package com.carmatech.algo.exercises;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.carmatech.algo.graphs.UndirectedGraph;

public class MazeWithGuardsTest {
	@Test
	public void findFarthestPointFromGuards() {
		UndirectedGraph graph = createMaze();
		int[] guards = new int[] { 3, 15 };

		MazeWithGuards mwg = new MazeWithGuards(graph, guards);
		List<Integer> points = mwg.findFarthestPointFromGuards();

		assertThat(points, is(not(nullValue())));

		System.out.println(points);
		assertThat(points, hasSize(1));
		assertThat(points, equalTo(Arrays.asList(21)));
	}

	private UndirectedGraph createMaze() {
		UndirectedGraph graph = new UndirectedGraph(24);
		graph.addEdge(0, 1);
		graph.addEdge(0, 6);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(7, 13);
		graph.addEdge(8, 9);
		graph.addEdge(9, 10);
		graph.addEdge(10, 11);
		graph.addEdge(10, 14);
		graph.addEdge(11, 12);
		graph.addEdge(12, 15);
		graph.addEdge(13, 16);
		graph.addEdge(14, 19);
		graph.addEdge(15, 20);
		graph.addEdge(16, 17);
		graph.addEdge(16, 21);
		graph.addEdge(17, 18);
		graph.addEdge(18, 19);
		graph.addEdge(18, 22);
		graph.addEdge(20, 23);
		return graph;
	}
}
