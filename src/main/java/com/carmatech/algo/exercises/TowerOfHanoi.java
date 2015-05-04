package com.carmatech.algo.exercises;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TowerOfHanoi {
    private static final int SOURCE = 0;
    private static final int NUM_PEGS = 3;

    private final Deque<Integer>[] pegs = createPegs();
    private final int numRings;

    public TowerOfHanoi(final int numRings) {
        this.numRings = numRings;
        for (int ring = numRings; ring > 0; --ring) {
            pegs[SOURCE].push(ring);
        }
    }

    private Deque<Integer>[] createPegs() {
        final Deque<Integer>[] pegs = (Deque<Integer>[]) new Deque[NUM_PEGS];
        for (int i = 0; i < NUM_PEGS; ++i) {
            pegs[i] = new ArrayDeque<>();
        }
        return pegs;
    }

    public void moveToPeg(final int destination) {
        if (destination < 0 || destination >= NUM_PEGS)
            throw new IllegalArgumentException();
        move(numRings, SOURCE, destination, NUM_PEGS - destination);
    }

    private void move(final int numRingsLeft, final int source, final int destination, final int buffer) {
        if (numRingsLeft == 0)
            return;
        move(numRingsLeft - 1, source, buffer, destination);
        doMove(source, destination);
        move(numRingsLeft - 1, buffer, destination, source);
    }

    private void doMove(final int source, final int destination) {
        final int ring = pegs[source].pop();
        pegs[destination].push(ring);
        System.out.println("Moved ring " + ring + " from peg #" + source + " to peg #" + destination + ".");
    }
}
