package com.ideaheap.barelyfunctional.data;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

/**
 * An immutable list library. Convenience functions for dealing with lists.
 */
public class BfLists {

    /**
     * Generates list primitive.
     *
     * @param elements elements to add.
     * @return An immutable list of the elements presented.
     */
    public static <T> List<T> list(
        T ... elements
    ) {
        return ImmutableList.copyOf(elements);
    }

    /**
     * combine all collections given into one immutable list. Collections are added in order.
     *
     * @param lists
     * @return
     */
    public static <T> List<T> merge(Collection<T>... lists) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();
        for (Collection<T> list : lists) {
            builder.addAll(list);
        }
        return builder.build();
    }

    /**
     * Return an updated list with the specified value at the specified index.
     *
     * @param list The list to create a new version of
     * @param idx the index for the value
     * @param val the new value
     * @return An updated list with the new value.
     */
    public static <T> List<T> assoc(List<T> list, int idx, T val) {
        return (List<T>) ImmutableList
            .builder()
            .addAll(list.subList(0, idx))
            .add(val)
            .addAll(list.subList(idx + 1, list.size()))
            .build();
    }

    /**
     * Add elements to the end of a list.
     * @param list The list to add to.
     * @param end The elements to add.
     * @return A new list with the elements specified added to the end.
     */
    public static <T> List<T> push(List<T> list, T... end) {
        return (List<T>) ImmutableList.builder().addAll(list).add(end).build();
    }

    /**
     * Add elements to the beginning of a list.
     *
     * @param list The list to add.
     * @param head The set of elements to put at the beginning of the list.
     * @return
     */
    public static <T> List<T> unshift(List<T> list, T... head) {
        return (List<T>) ImmutableList.builder().add(head).addAll(list).build();
    }

}
