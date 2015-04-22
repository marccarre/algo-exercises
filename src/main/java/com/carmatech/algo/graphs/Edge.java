package com.carmatech.algo.graphs;

import com.google.common.base.Objects;

public class Edge implements Comparable<Edge> {
	private final int v;
	private final int w;
	private final double weight;

	public Edge(final int v, final int w, final double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int either() {
		return v;
	}

	public int other() {
		return w;
	}

	public int other(final int vertex) {
		return (vertex == v) ? w : v;
	}

	public double weight() {
		return weight;
	}

	@Override
	public int compareTo(final Edge that) {
		return Double.compare(this.weight, that.weight);
	}

	@Override
	public String toString() {
		return "(" + v + ") <---[" + weight + "]---> (" + w + ")";
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
		return Objects.equal(this.v, that.v) && Objects.equal(this.w, that.w) && Objects.equal(this.weight, that.weight);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(v, w, weight);
	}
}
