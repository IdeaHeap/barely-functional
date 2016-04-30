package com.ideaheap.barelyfunctional.data;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An immutable set library. Provides convenience functions for dealing with sets.
 */
public class BfSets {

    /**
     * Create a set with the values specified
     * @param values the values to use.
     * @return A set with the values specified.
     */
    public static <T> Set<T> set(T... values) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        builder.add(values);
        return builder.build();
    }

    /**
     * Add the values specified to the set. Dedup any duplicates.
     * @param set the set to start with.
     * @param vals the values to add.
     * @return A set with vals added to it.
     */
    public static <T> Set<T> assoc(Set<T> set, T... vals) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        builder.addAll(set);
        builder.add(vals);
        return builder.build();
    }

    /**
     * Remove the values specified from the set. This does not care if the value was there to
     * start with.
     * @param set The set to remove values from
     * @param vals The values to remove
     * @return A set minus the values mentioned.
     */
    public static <T> Set<T> dissoc(Set<T> set, T... vals) {
        return not(set, set(vals));
    }

    /**
     * Create a set that is the union of a number of other sets. This is the set of elements
     * from all sets.
     * @param sets The sets to union.
     * @return A set that is the union of the sets given.
     */
    public static <T> Set<T> union(Set<T>... sets) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        for (Set<T> set : sets) {
            builder.addAll(set);
        }
        return builder.build();
    }

    /**
     * Create a set that is the intersection of a number of other sets. An intersection are the
     * values that are in all sets.
     * @param sets The sets to intersect.
     * @return A set with the values that were found in every set.
     */
    public static <T> Set<T> intersection(Set<T>... sets) {
        return Arrays.asList(sets)
                     .stream()
                     .reduce((set1, set2) -> Sets.intersection(set1, set2))
                     .get();

    }

    /**
     * Create a set with the elements that are unique in each of the sets given.
     * @param sets The sets to look for unique elements
     * @return The set of elements that were only found once in any set.
     */
    public static <T> Set<T> xor(Set<T>... sets) {
        return Arrays.asList(sets)
                     .stream()
                     .reduce((set1, set2) -> not(
                         Sets.union(set1, set2),
                         Sets.intersection(set1, set2)
                     )).get();
    }

    /**
     * Create a set that is the given set minus any of the elements specified. This does not care
     * if the elements were in there or not.
     *
     * @param set the set to start from
     * @param removals the elements to remove from the set.
     * @return The set minus the elements set for removal.
     */
    public static <T> Set<T> not(Set<T> set, Set<T>... removals) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();

        Set<T> updateSet = new HashSet<>();
        updateSet.addAll(set);
        for (Set<T> removal : removals) {
            updateSet.removeAll(removal);
        }
        builder.addAll(updateSet);
        return builder.build();
    }
}
