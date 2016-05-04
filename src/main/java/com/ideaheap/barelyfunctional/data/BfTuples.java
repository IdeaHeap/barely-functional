package com.ideaheap.barelyfunctional.data;

import org.javatuples.*;

/**
 * A wrapper around javatuples. Javatuples does all the hard stuff, but they didn't make a generic
 * function call to just instantiate tuples. Here's that function / functions.
 *
 * As a note, all tuple types extend Tuple, so if you don't know what type is coming in, you can
 * use "? extends Tuple".
 *
 */
public class BfTuples {

    /**
     * Create a Unit tuple.
     * @param v0 the value for this tuple
     * @param <V0> the type for this.
     * @return A Unit tuple.
     */
    public static <V0> Unit<V0> tuple(V0 v0) {
        return new Unit<>(v0);
    }

    /**
     * Create a Pair tuple.
     * @param v0
     * @param v1
     * @param <V0>
     * @param <V1>
     * @return
     */
    public static <V0, V1> Pair<V0, V1> tuple(V0 v0, V1 v1) {
        return new Pair<>(v0, v1);
    }

    /**
     * Create a Triple tuple.
     * @param v0
     * @param v1
     * @param v2
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @return
     */
    public static <V0, V1, V2> Triplet<V0, V1, V2> tuple(V0 v0, V1 v1, V2 v2) {
        return new Triplet<>(v0, v1, v2);
    }

    /**
     * Create a quartet tuple
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @return
     */
    public static <V0, V1, V2, V3> Quartet<V0, V1, V2, V3> tuple(V0 v0, V1 v1, V2 v2, V3 v3) {
        return new Quartet<>(v0, v1, v2, v3);
    }

    /**
     * Create a Quintet tuple.
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @param <V4>
     * @return
     */
    public static <V0, V1, V2, V3, V4> Quintet<V0, V1, V2, V3, V4> tuple(
        V0 v0,
        V1 v1,
        V2 v2,
        V3 v3,
        V4 v4
    ) {
        return new Quintet<>(v0, v1, v2, v3, v4);
    }

    /**
     * Create a Sextet tuple.
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param v5
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @param <V4>
     * @param <V5>
     * @return
     */
    public static <V0, V1, V2, V3, V4, V5> Sextet<V0, V1, V2, V3, V4, V5> tuple(
        V0 v0,
        V1 v1,
        V2 v2,
        V3 v3,
        V4 v4,
        V5 v5
    ) {
        return new Sextet<>(v0, v1, v2, v3, v4, v5);
    }

    /**
     * Create a Septet tuple.
     *
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param v5
     * @param v6
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @param <V4>
     * @param <V5>
     * @param <V6>
     * @return
     */
    public static <V0, V1, V2, V3, V4, V5, V6> Septet<V0, V1, V2, V3, V4, V5, V6> tuple(
        V0 v0,
        V1 v1,
        V2 v2,
        V3 v3,
        V4 v4,
        V5 v5,
        V6 v6
    ) {
        return new Septet<>(v0, v1, v2, v3, v4, v5, v6);
    }

    /**
     * Create an octet tuple.
     * @param v0
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param v5
     * @param v6
     * @param v7
     * @param <V0>
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @param <V4>
     * @param <V5>
     * @param <V6>
     * @param <V7>
     * @return
     */
    public static <V0, V1, V2, V3, V4, V5, V6, V7> Octet<V0, V1, V2, V3, V4, V5, V6, V7> tuple(
        V0 v0,
        V1 v1,
        V2 v2,
        V3 v3,
        V4 v4,
        V5 v5,
        V6 v6,
        V7 v7
    ) {
        return new Octet<>(v0, v1, v2, v3, v4, v5, v6, v7);
    }

}
