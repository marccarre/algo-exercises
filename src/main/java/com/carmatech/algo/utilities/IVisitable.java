package com.carmatech.algo.utilities;

import java.util.function.Function;

public interface IVisitable<T> {
    <Out> Out visit(final Function<T, Out> visitor);
}
