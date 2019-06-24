/*
 * File: StringMap.java
 * --------------------
 * This file defines the interface for a simplification of the Map class
 * in which the keys and values are always strings and the interface
 * specifies only the get and put methods.
 */

package edu.stanford.cs.javacs2.ch14;

public interface StringMap {

/**
 * Sets the binding of key to value.
 */

   public void put(String key, String value);

/**
 * Returns the value associated with key, or null if none exists.
 */

   public String get(String key);

}
