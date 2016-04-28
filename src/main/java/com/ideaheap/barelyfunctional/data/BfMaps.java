package com.ideaheap.barelyfunctional.data;

import com.google.common.collect.ImmutableMap;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * An immutable map library. Convenience functions for dealing with maps and entries.
 */
public class BfMaps {
    /**
     * merges N maps. The expectation here is that there are no duplicate keys between maps. An
     * exception will be thrown if there is.
     * If you want that functionality, use the assoc method.
     *
     * @param maps the maps to merge
     * @return A map containing the elements from all maps it was given merged together.
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
     * @param map The map to update
     * @param entries The entries to add to the map.
     * @return A map with the entries either added or updated to it from the original map.
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


    /**
     * Update map with content from upsertMap.
     * @param map The map to update.
     * @param upsertMap The map to source for updates.
     * @return An updated map with the elements specified updated or added.
     */
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

    /**
     * Drop the keys specified from the map specified
     * @param map The map to start from.
     * @param keys The keys to remove
     * @return A copy of map that has the keys specified removed from it.
     */
    public static <K, V> Map<K, V> dissoc(Map<K, V>map, K ... keys) {
        Map<K, V> comboMap = new HashMap<K, V>();
        comboMap.putAll(map);
        for (K key : keys) {
            if (comboMap.containsKey(key)) {
                comboMap.remove(key);
            }
        }

        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();
        builder.putAll(comboMap);
        return builder.build();
    }

    /**
     * Create a map with the specified entries.
     * @param entries the entries to add to the map.
     * @return A map with the entries specified.
     */
    public static <K, V> Map<K, V> map(Map.Entry<K, V>... entries) {
        ImmutableMap.Builder<K, V> builder = ImmutableMap.builder();

        for (Map.Entry<K, V> entry : entries) {
            builder.put(entry);
        }

        return builder.build();
    }

    /**
     * Update or replace key with val.
     *
     * @param map The map to create an updated version of
     * @param key The key to either update or add.
     * @param val The value to place at this key.
     * @return An updated map with key : val added / updated.
     */
    public static <K, V> Map<K, V> assoc(Map<K, V> map, K key, V val) {
        return assoc(map, e(key, val));
    }

    /**
     * Create an entry with key : val.
     * @param key The key.
     * @param val The value.
     * @return A map entry with key, value.
     */
    public static <K, V> Map.Entry<K, V> e(K key, V val) {
        return new AbstractMap.SimpleImmutableEntry<K, V>(key, val);
    }

}
