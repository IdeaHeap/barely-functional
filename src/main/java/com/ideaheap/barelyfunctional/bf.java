package com.ideaheap.barelyfunctional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by nwertzberger on 4/27/16.
 */
public class Bf {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Lists

    /**
     * Generates list primitive.
     *
     * @param elements
     * @param <T>
     * @return
     */
    public static <T> List<T> l(
        T... elements
    ) {
        return ImmutableList.copyOf(elements);
    }

    /**
     * combine all lists given into one list. Lists are added in order.
     *
     * @param lists
     * @param <T>
     * @return
     */
    public static <T> List<T> merge(List<T>... lists) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();
        for (List<T> list : lists) {
            builder.addAll(list);
        }
        return builder.build();
    }

    /**
     * Return an updated list with the specified value at the specified index.
     *
     * @param list
     * @param idx
     * @param val
     * @param <T>
     * @return
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
     * Update or replace key with val.
     *
     * @param map
     * @param key
     * @param val
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> assoc(Map<K, V> map, K key, V val) {
        return assoc(map, e(key, val));
    }

    public static <T> List<T> push(List<T> list, T... end) {
        return (List<T>) ImmutableList.builder().addAll(list).add(end).build();
    }

    public static <T> List<T> unshift(List<T> tail, T... head) {
        return (List<T>) ImmutableList.builder().add(head).addAll(tail).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Maps


    /**
     * merges N maps. The expectation here is that there are no duplicate keys between maps.
     * If you want that functionality, use the assoc method.
     *
     * @param maps
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> merge(Map<K, V>... maps) {
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        for (Map<K, V> map : maps) {
            builder.putAll(map);
        }
        return builder.build();
    }

    /**
     * Update or replace with e(key, val)
     *
     * @param map
     * @param entries
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> assoc(Map<K, V> map, Map.Entry<K, V>... entries) {
        Map<K, V> comboMap = new HashMap<K, V>();
        comboMap.putAll(map);
        for (Map.Entry<K, V> entry : entries) {
            comboMap.put(entry.getKey(), entry.getValue());
        }

        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        builder.putAll(comboMap);
        return builder.build();
    }


    public static <K, V> Map<K, V> assoc(Map<K, V> map, Map<K, V> upsertMap) {
        Map<K, V> comboMap = new HashMap<K, V>();
        comboMap.putAll(map);
        for (Map.Entry<K, V> entry : upsertMap.entrySet()) {
            comboMap.put(entry.getKey(), entry.getValue());
        }

        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        builder.putAll(comboMap);
        return builder.build();
    }

    public static <K, V> Map<K, V> m(Map.Entry<K, V>... entries) {
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();

        for (Map.Entry<K, V> entry : entries) {
            builder.put(entry);
        }

        return builder.build();
    }

    public static <K, V> Map.Entry<K, V> e(K key, V val) {
        return new AbstractMap.SimpleImmutableEntry<K, V>(key, val);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Sets

    public static <T> Set<T> s(T... values) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        builder.add(values);
        return builder.build();
    }

    public static <T> Set<T> assoc(Set<T> set, T... vals) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        builder.addAll(set);
        builder.add(vals);
        return builder.build();
    }

    public static <T> Set<T> union(Set<T>... sets) {
        ImmutableSet.Builder<T> builder = ImmutableSet.builder();
        for (Set<T> set : sets) {
            builder.addAll(set);
        }
        return builder.build();
    }

    public static <T> Set<T> intersection(Set<T>... sets) {
        return Arrays.asList(sets)
                     .stream()
                     .reduce((set1, set2) -> Sets.intersection(set1, set2))
                     .get();

    }

    public static <T> Set<T> xor(Set<T>... sets) {
        return Arrays.asList(sets)
                     .stream()
                     .reduce((set1, set2) -> not(
                         Sets.union(set1, set2),
                         Sets.intersection(set1, set2)
                     )).get();
    }

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
