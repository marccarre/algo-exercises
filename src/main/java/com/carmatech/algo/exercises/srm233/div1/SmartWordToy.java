package com.carmatech.algo.exercises.srm233.div1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TopCoder SRM 233 DIV 1 500-point problem.
 */
public class SmartWordToy {
	private static final int NUM_DIMENSIONS = 4;
	private static final int NUM_POSSIBILITIES = 26;
	private static final int SIZE = (int) Math.pow(NUM_POSSIBILITIES, NUM_DIMENSIONS);
	private static final int ROOT = -1;

	public int minPresses(final String start, final String finish, final String[] forbid) {
		final boolean[] forbidden = new boolean[SIZE];
		final boolean[] visited = new boolean[SIZE];
		final int[] pathTo = pathTo();

		addConstraints(forbidden, forbid);
		if (forbidden[toPosition(start)] || forbidden[toPosition(finish)])
			return -1;

		breadthFirstSearch(visited, forbidden, pathTo, start);
		return countSteps(pathTo, start, finish);
	}

	private int[] pathTo() {
		final int[] pathTo = new int[SIZE];
		for (int i = 0; i < SIZE; ++i)
			pathTo[i] = ROOT;
		return pathTo;
	}

	private void addConstraints(final boolean[] forbidden, final String[] forbid) {
		for (final String constraint : forbid) {
			final String[] groups = constraint.split(" ");
			for (int i = 0; i < groups[0].length(); ++i) {
				for (int j = 0; j < groups[1].length(); ++j) {
					for (int k = 0; k < groups[2].length(); ++k) {
						for (int l = 0; l < groups[3].length(); ++l) {
							final char c1 = groups[0].charAt(i);
							final char c2 = groups[1].charAt(j);
							final char c3 = groups[2].charAt(k);
							final char c4 = groups[3].charAt(l);
							final int position = toPosition(c1, c2, c3, c4);
							forbidden[position] = true;
						}
					}
				}
			}
		}
	}

	private void breadthFirstSearch(final boolean[] visited, final boolean[] forbidden, final int[] pathTo, final String start) {
		final Queue<Integer> queue = new LinkedList<Integer>();

		final int startPosition = toPosition(start);
		queue.add(startPosition);
		visited[startPosition] = true;

		while (!queue.isEmpty()) {
			final int current = queue.poll();

			for (final int neighbour : neighbours(current)) {
				if (!visited[neighbour] && !forbidden[neighbour]) {
					visited[neighbour] = true;
					pathTo[neighbour] = current;
					queue.add(neighbour);
				}
			}
		}
	}

	private int countSteps(final int[] pathTo, final String start, final String finish) {
		int count = 0;
		int position = toPosition(finish);

		if (pathTo[position] == ROOT)
			return -1;

		while (position != ROOT) {
			position = pathTo[position];
			++count;
		}
		return count - 1;
	}

	private int[] neighbours(final int position) {
		final int numNeighbours = (int) Math.pow(2, NUM_DIMENSIONS);
		final int[] neighbours = new int[numNeighbours];
		final int[] coordinates = toCoordinates(position);
		int neighbour = 0;
		for (int i = 0; i < NUM_DIMENSIONS; ++i) {
			final int originalValue = coordinates[i];

			coordinates[i] = rotateUp(coordinates[i]);
			neighbours[neighbour] = toPosition(coordinates);
			++neighbour;

			coordinates[i] = originalValue;

			coordinates[i] = rotateDown(coordinates[i]);
			neighbours[neighbour] = toPosition(coordinates);
			++neighbour;

			coordinates[i] = originalValue;
		}
		return neighbours;
	}

	private int rotateUp(final int originalValue) {
		return rotate(originalValue, true);
	}

	private int rotateDown(final int originalValue) {
		return rotate(originalValue, false);
	}

	private int rotate(final int originalValue, final boolean up) {
		int newValue = up ? originalValue + 1 : originalValue - 1;
		if (newValue == 26)
			newValue = 0;
		else if (newValue == -1)
			newValue = 25;
		return newValue;
	}

	private int[] toCoordinates(int position) {
		final int[] coordinates = new int[NUM_DIMENSIONS];
		coordinates[0] = position % NUM_POSSIBILITIES;
		position /= NUM_POSSIBILITIES;
		coordinates[1] = position % NUM_POSSIBILITIES;
		position /= NUM_POSSIBILITIES;
		coordinates[2] = position % NUM_POSSIBILITIES;
		position /= NUM_POSSIBILITIES;
		coordinates[3] = position % NUM_POSSIBILITIES;
		return coordinates;
	}

	private int toInt(final char c) {
		return c - 'a';
	}

	private int toCoordinate(final char c, final int dimension) {
		return toCoordinate(toInt(c), dimension);
	}

	private int toCoordinate(final int c, final int dimension) {
		return (int) (c * Math.pow(NUM_POSSIBILITIES, dimension));
	}

	private int toPosition(final int[] coordinates) {
		final int coord1 = toCoordinate(coordinates[0], 0);
		final int coord2 = toCoordinate(coordinates[1], 1);
		final int coord3 = toCoordinate(coordinates[2], 2);
		final int coord4 = toCoordinate(coordinates[3], 3);
		final int position = coord1 + coord2 + coord3 + coord4;
		return position;
	}

	private int toPosition(final String s) {
		final int coord1 = toCoordinate(s.charAt(0), 0);
		final int coord2 = toCoordinate(s.charAt(1), 1);
		final int coord3 = toCoordinate(s.charAt(2), 2);
		final int coord4 = toCoordinate(s.charAt(3), 3);
		final int position = coord1 + coord2 + coord3 + coord4;
		return position;
	}

	private int toPosition(final char c1, final char c2, final char c3, final char c4) {
		final int coord1 = toCoordinate(c1, 0);
		final int coord2 = toCoordinate(c2, 1);
		final int coord3 = toCoordinate(c3, 2);
		final int coord4 = toCoordinate(c4, 3);
		final int position = coord1 + coord2 + coord3 + coord4;
		return position;
	}
}