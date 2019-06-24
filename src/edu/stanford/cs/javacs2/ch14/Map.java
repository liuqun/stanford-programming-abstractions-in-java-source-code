/*
 * File: Map.java
 * --------------
 * This file defines the interface for a class that implements a Map
 * that associates keys and values.
 */

package edu.stanford.cs.javacs2.ch14;

import java.util.Set;

/**
 * This interface defines the structure of a map from keys of type K to
 * values of type V.
 */

public interface Map<K,V> {

/**
 * Returns the number of key-value pairs in the map.
 *
 * @return The number of key-value pairs in the map
 */

   public int size();

/**
 * Returns true if this map contains no key-value pairs.
 *
 * @return The value true if this map is empty
 */

   public boolean isEmpty();

/**
 * Removes all key-value pairs from this map.
 */

   public void clear();

/**
 * Sets the binding of key to value.
 *
 * @param key The key
 * @param value The new value for key
 */

   public void put(K key, V value);

/**
 * Returns the value associated with key, or null if none exists.
 *
 * @param key The key
 * @return The value associated with key, or null if none exists
 */

   public V get(K key);

/**
 * Returns true if the map contains a binding for the specified key.
 *
 * @param key The key
 * @return The value true if the map contains a binding for key
 */

   public boolean containsKey(K key);

/**
 * Removes the specified key from the map.
 *
 * @param key The key
 */

   public void remove(K key);

/**
 * Returns a set of all the keys in the map.
 *
 * @return A set of all the keys in the map
 */

   public Set<K> keySet();

}
