package com.ideaheap.barelyfunctional.functions;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.MapMaker;
import org.javatuples.Pair;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.ideaheap.barelyfunctional.data.BfTuples.tuple;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.T;

/**
 * Memoization is the act of storing results from previous runs of a function and using these
 * instead. These are often useful when calling long running services that rarely change, and
 * where getting a stale value is not a big deal. They are also useful if a recursive function
 * ends up relatively deep recursion with common sub-paths.
 *
 * These should be used with site-effect free functions! There are no guarantees to the number
 * of times that the target function will be called with the parameters given.
 */
public class Memoizer {

    private final CacheBuilder builder;

    /**
     * Constructor to specify custom Guava CacheBuilder.
     * @param builder
     */
    public Memoizer(CacheBuilder builder) {
        this.builder = builder;
    }

    /**
     * Memoize a single function call.
     *
     * @param function The function to memoize
     * @param <T>
     * @param <R>
     * @return A memoized version of the given function, backed by its own instance of a cache
     * build using the builder specified.
     */
    public <T, R> Function<T, R> memoize(Function<T, R> function) {
        final Cache<T, Optional<R>> entries = builder.build();
        return (T t) -> {
            Optional<R> ifPresent = entries.getIfPresent(t);
            if (ifPresent == null) {
                R result = function.apply(t);
                entries.put(t, Optional.fromNullable(result));
                return result;
            } else {
                return ifPresent.orNull();
            }
        };
    }

    /**
     * Memoize a two parameter function call.
     *
     * @param biFunction The function to memoize
     * @param <T>
     * @param <R>
     * @return A memoized version of the given function, backed by its own instance of a cache
     * build using the builder specified.
     */
    public <T, U, R> BiFunction<T, U, R> memoize(BiFunction<T, U, R> biFunction) {
        Function<Pair<T, U>, R> memo = memoize((Pair<T, U> pair) -> {
            return biFunction.apply(pair.getValue0(), pair.getValue1());
        });
        return (T t, U u) -> memo.apply(tuple(t, u));
    }
}
