package com.carmatech.algo.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import com.carmatech.algo.graphs.UndirectedGraph;

public class MazeWithGuards {
	private final UndirectedGraph graph;
	private final int numVertices;
	private final int[] guards;
	private final Set<Integer> isGuard;
	private final int numGuards;

	public MazeWithGuards(final UndirectedGraph graph, final int[] guards) {
		this.graph = graph;
		this.numVertices = graph.numVertices();
		this.guards = guards;
		this.numGuards = guards.length;

		isGuard = new HashSet<Integer>();
		for (int i = 0; i < numGuards; ++i)
			isGuard.add(guards[i]);
	}

	public List<Integer> findFarthestPointFromGuards() {
		final Map<Integer, int[]> distanceFromGuards = new HashMap<Integer, int[]>();

		for (int i = 0; i < numGuards; ++i)
			bfs(i, distanceFromGuards);

		return pointsForMaxOfMins(distanceFromGuards);
	}

	private void bfs(final int guardIndex, final Map<Integer, int[]> distanceFromGuards) {
		final Queue<Integer> queue = new LinkedList<Integer>();
		final Queue<Integer> distances = new LinkedList<Integer>();
		final boolean[] visited = new boolean[numVertices];

		final int guard = guards[guardIndex];
		visited[guard] = true;
		queue.add(guard);
		distances.add(0);

		while (!queue.isEmpty()) {
			final int point = queue.poll();
			final int distance = distances.poll() + 1;

			for (final int neighbour : graph.neighbours(point)) {
				if (!visited[neighbour] && !isGuard.contains(neighbour)) {
					visited[neighbour] = true;
					queue.add(neighbour);
					distances.add(distance);

					if (!distanceFromGuards.containsKey(neighbour))
						distanceFromGuards.put(neighbour, new int[numGuards]);
					distanceFromGuards.get(neighbour)[guardIndex] = distance;
				}
			}
		}
	}

	private List<Integer> pointsForMaxOfMins(final Map<Integer, int[]> distanceFromGuards) {
		final Map<Integer, List<Integer>> sortedPoints = new TreeMap<Integer, List<Integer>>();

		int max = Integer.MIN_VALUE;

		for (final Entry<Integer, int[]> distance : distanceFromGuards.entrySet()) {
			final int point = distance.getKey();
			final int min = minDistanceFromGuard(distance);

			if (!sortedPoints.containsKey(min))
				sortedPoints.put(min, new LinkedList<Integer>());
			sortedPoints.get(min).add(point);

			if (min > max)
				max = min;
		}

		return sortedPoints.get(max);
	}

	private int minDistanceFromGuard(final Entry<Integer, int[]> distance) {
		final int[] distances = distance.getValue();
		Arrays.sort(distances);
		final int min = distances[0];
		return min;
	}
}
