package com.ideaheap.barelyfunctional.functions;

import com.google.common.cache.CacheBuilder;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Default memoizers with default weakValued cache. Weak valued caches were chosen because they
 * represented the least likely choice to end up running a person out of memory if they were
 * using this library without actually understanding the memory consequences of pure memoization.
 *
 * If you want more control, use the Memoizer class, and pass it a different CacheBuilder through
 * its constructor.
 */
public class Memoizers {
    private static Memoizer defaultMemoizer = new Memoizer(CacheBuilder.newBuilder().weakValues());

    /**
     * Memoize a function.
     *
     * @param function the function to memoize
     * @param <T>
     * @param <R>
     * @return A memoized version of the function.
     */
    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        return defaultMemoizer.memoize(function);

    }

    /**
     * Memoize a BiFunction.
     *
     * @param biFunction the function to memoize.
     * @param <T>
     * @param <U>
     * @param <R>
     * @return A memoized version of the given function.
     */
    public static <T, U, R> BiFunction<T, U, R> memoize(BiFunction<T, U, R> biFunction) {
        return defaultMemoizer.memoize(biFunction);
    }
}
