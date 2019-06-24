/*
 * File: KeyValuePair.java
 * -----------------------
 * This file exports a generic class that stores an arbitrary key-value pair.
 */

package edu.stanford.cs.javacs2.ch13;

/**
 * This class stores an encapsulated key-value pair.
 */

public class KeyValuePair<K,V> {

/**
 * Creates a new key-value pair.
 */

   public KeyValuePair(K key, V value) {
      this.key = key;
      this.value = value;
   }

/**
 * Retrieves the key component of a key-value pair.
 */

   public K getKey() {
      return key;
   }

/**
 * Retrieves the value component of a key-value pair.
 */

   public V getValue() {
      return value;
   }

/**
 * Converts a key-value pair to a readable string representation.
 */

   @Override
   public String toString() {
      return "<" + key + ", " + value + ">";
   }

/* Private instance variables */

   private K key;
   private V value;

}
