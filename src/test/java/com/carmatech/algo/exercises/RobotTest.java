package com.carmatech.algo.exercises;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RobotTest {
    @Test
    public void descendingThenAscending() {
        // Path = \/
        Point[] path = { new Point(0, 0, 1), new Point(1, 0, 0), new Point(2, 0, 1) };
        assertThat(Robot.heightToPowerFor(path), is(1.0));
    }

    @Test
    public void ascendingThenDescending() {
        // Path = /\
        Point[] path = { new Point(0, 0, 0), new Point(1, 0, 1), new Point(2, 0, 0) };
        assertThat(Robot.heightToPowerFor(path), is(1.0));
    }

    @Test
    public void ascendingThenDescendingThenAscending() {
        // Path = /\/
        Point[] path = { new Point(0, 0, 0), new Point(1, 0, 1), new Point(2, 0, 0), new Point(3, 0, 1) };
        assertThat(Robot.heightToPowerFor(path), is(1.0));
    }

    @Test
    public void ascendingThenDescendingTwiceAsMuchThenAscending() {
        // Path = /\  /
        //          \/
        Point[] path = { new Point(0, 0, 1), new Point(1, 0, 2), new Point(2, 0, 0), new Point(3, 0, 1.5) };
        assertThat(Robot.heightToPowerFor(path), is(1.5));
    }
}
