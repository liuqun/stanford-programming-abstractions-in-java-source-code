/*
 * File: HashStringMap.java
 * ------------------------
 * This file implements the StringMap interface using a hash table.
 */

package edu.stanford.cs.javacs2.ch14;

public class HashStringMap implements StringMap {

/*
 * Implementation notes: constructor
 * ---------------------------------
 * The constructor creates the buckets array, in which each element is a
 * linked list of key-value pairs.
 */

   public HashStringMap() {
      nBuckets = INITIAL_BUCKET_COUNT;
      buckets = new Cell[nBuckets];
   }

/*
 * Implementation notes: get
 * -------------------------
 * The get method calls findCell to search the linked list for the
 * matching key.  If no key is found, get returns null.
 */

   public String get(String key) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      Cell cp = findCell(bucket, key);
      return (cp == null) ? null : cp.value;
   }

/*
 * Implementation notes: put
 * -------------------------
 * If findCell can't find a matching key, the put method creates a new
 * cell and adds it to the front of the chain for that bucket.
 */

   public void put(String key, String value) {
      int bucket = Math.abs(key.hashCode()) % nBuckets;
      Cell cp = findCell(bucket, key);
      if (cp == null) {
         cp = new Cell();
         cp.key = key;
         cp.link = buckets[bucket];
         buckets[bucket] = cp;
      }
      cp.value = value;
   }

/*
 * Implementation notes: findCell
 * ------------------------------
 * This private method looks for a key in the specified bucket chain to
 * find a matching key.  If the key is found, findCell returns it.  If
 * no match is found, findCell returns null.
 */

   private Cell findCell(int bucket, String key) {
      for (Cell cp = buckets[bucket]; cp != null; cp = cp.link) {
         if (cp.key.equals(key)) return cp;
      }
      return null;
   }

/* Inner class to represent a cell in the linked list */

   private static class Cell {
      String key;
      String value;
      Cell link;
   }

/* Constants */

   private static final int INITIAL_BUCKET_COUNT = 14;

/* Private instance variables */

   private Cell[] buckets;
   private int nBuckets;

}
