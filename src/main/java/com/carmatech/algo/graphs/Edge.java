package com.carmatech.algo.graphs;

import com.google.common.base.Objects;

public class Edge implements Comparable<Edge> {

	private final int from;
	private final int to;
	private final double weight;

	public Edge(final int from, final int to, final double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int either() {
		return from;
	}

	public int other(final int v) {
		return (v == from) ? to : from;
	}

	public double weight() {
		return weight;
	}

	@Override
	public int compareTo(final Edge that) {
		if (this.weight < that.weight)
			return -1;
		if (this.weight > that.weight)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "(" + from + ") <---[" + weight + "]---> (" + to + ")";
	}

	@Override
	public boolean equals(final Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof Edge))
			return false;
		final Edge that = (Edge) object;
		return Objects.equal(this.from, that.from) && Objects.equal(this.to, that.to) && Objects.equal(this.weight, that.weight);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(from, to, weight);
	}
}
