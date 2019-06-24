/*
 * File: HashMap.java
 * ------------------
 * This file implements the Map interface using a hash table.
 */

package edu.stanford.cs.javacs2.ch14;

import edu.stanford.cs.javacs2.ch13.GenericArray;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashMap<K,V> implements Map<K,V> {

/*
 * Implementation notes: HashMap constructor
 * -----------------------------------------
 * The constructor creates a GenericArray to hold the linked lists that
 * store the key-value pairs for each bucket.  In the exercises, you will
 * have a chance to extend this class so that it expands the table when
 * the load factor becomes too high.
 */

   public HashMap() {
      nBuckets = INITIAL_BUCKET_COUNT;
      buckets = new GenericArray<Cell>(nBuckets);
      count = 0;
   }

/*
 * Implementation notes: size, isEmpty, clear
 * ------------------------------------------
 * These methods are simple to implement because the number of elements
 * is stored in the instance variable count.
 */

   public int size() {
      return count;
   }

   public boolean isEmpty() {
      return count == 0;
   }

   public void clear() {
      count = 0;
      for (int i = 0; i < nBuckets; i++) {
         buckets.set(i, null);
      }
   }

/*
 * Implementation notes: get, put, containsKey
 * -------------------------------------------
 * These methods all use a private method called findCell to find the key.
 */

   public V get(K key) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      Cell cp = findCell(bucket, key);
      return (cp == null) ? null : cp.value;
   }

   public void put(K key, V value) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      Cell cp = findCell(bucket, key);
      if (cp == null) {
         cp = new Cell();
         cp.key = key;
         cp.link = buckets.get(bucket);
         buckets.set(bucket, cp);
         count++;
      }
      cp.value = value;
   }

   public boolean containsKey(K key) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      return findCell(bucket, key) != null;
   }

/*
 * Implementation notes: remove
 * ----------------------------
 * This method stores the cell before the target cell in the variable prev.
 */

   public void remove(K key) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      Cell cp = buckets.get(bucket);
      Cell prev = null;
      while (cp != null && !cp.key.equals(key)) {
         prev = cp;
         cp = cp.link;
      }
      if (cp != null) {
         if (prev == null) {
            buckets.set(bucket, cp.link);
         } else {
            prev.link = cp.link;
         }
         count--;
      }
   }

/*
 * Implementation notes: keySet
 * ----------------------------
 * This method assembles the set by iterating through each bucket chain.
 */

   public Set<K> keySet() {
      Set<K> keys = new HashSet<K>();
      for (int i = 0; i < buckets.size(); i++) {
         for (Cell cp = buckets.get(i); cp != null; cp = cp.link) {
            keys.add(cp.key);
         }
      }
      return keys;
   }

/*
 * Implementation notes: keyIterator
 * ---------------------------------
 * This method is not part of the HashMap interface but is exported here to
 * allow the HashSet class to implement an iterator.  The code builds an
 * ArrayList containing the keys and then returns the ArrayList iterator.
 */

   public Iterator<K> keyIterator() {
      ArrayList<K> keys = new ArrayList<K>();
      for (int i = 0; i < buckets.size(); i++) {
         for (Cell cp = buckets.get(i); cp != null; cp = cp.link) {
            keys.add(cp.key);
         }
      }
      return keys.iterator();
   }

/*
 * Implementation notes: findCell
 * ------------------------------
 * This private method looks for a key in the specified bucket chain to
 * find a matching key.  If the key is found, findCell returns it.  If
 * no match is found, findCell returns null.
 */

   private Cell findCell(int bucket, K key) {
      Cell cp = buckets.get(bucket);
      while (cp != null && !cp.key.equals(key)) {
         cp = cp.link;
      }
      return cp;
   }

/* Inner class for a cell in the linked lists for the bucket chains */

   private class Cell {
      K key;
      V value;
      Cell link;
   }

/* Constants */

   private static final int INITIAL_BUCKET_COUNT = 7;

/* Private instance variables */

   private GenericArray<Cell> buckets;
   private int nBuckets;
   private int count;

}
