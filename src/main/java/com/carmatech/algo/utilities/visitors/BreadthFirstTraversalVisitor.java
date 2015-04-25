package com.carmatech.algo.utilities.visitors;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

public class BreadthFirstTraversalVisitor<Vertex extends Iterable<Vertex>> implements Function<Vertex, Map<Vertex, Vertex>> {
    @Override
    public Map<Vertex, Vertex> apply(final Vertex root) {
        return Collections.unmodifiableMap(
            (root == null)
                ? new LinkedHashMap<Vertex, Vertex>()
                : new BreadthFirstTraversal().traverseFrom(root)
        );
    }

    private class BreadthFirstTraversal {
        private final Map<Vertex, Vertex> path = new LinkedHashMap<>();
        private final Set<Vertex> visited = new HashSet<>();
        private final Queue<Vertex> nextVertices = new ArrayDeque<>();

        private Map<Vertex, Vertex> traverseFrom(final Vertex root) {
            if (root == null)
                return path;

            path.put(root, root);
            visitNeighbours(root);

            while (!nextVertices.isEmpty())
                visitNeighbours(nextVertices.poll());

            return path;
        }

        private void visitNeighbours(final Vertex source) {
            for (final Vertex neighbour : source) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    path.put(neighbour, source);
                    nextVertices.add(neighbour);
                }
            }
        }
    }
}
