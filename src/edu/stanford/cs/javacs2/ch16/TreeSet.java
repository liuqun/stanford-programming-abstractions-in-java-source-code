/*
 * File: TreeSet.java
 * ------------------
 * This file implements the TreeSet class using an underlying TreeMap.
 * Every method body is one line long because TreeSet can simply forward
 * the operation to the underlying TreeMap.
 */

package edu.stanford.cs.javacs2.ch16;

import edu.stanford.cs.javacs2.ch15.TreeMap;
import java.util.Iterator;

public class TreeSet<T extends Comparable<? super T>> implements Set<T> {

   public TreeSet() {
      map = new TreeMap<T,Boolean>();
   }

   public int size() {
      return map.size();
   }

   public boolean isEmpty() {
      return map.isEmpty();
   }

   public void clear() {
      map.clear();
   }

   public void add(T value) {
      map.put(value, true);
   }

   public void remove(T value) {
      map.remove(value);
   }

   public boolean contains(T value) {
      return map.containsKey(value);
   }

   public Iterator<T> iterator() {
      return map.keyIterator();
   }

/* Private instance variables */

   private TreeMap<T,Boolean> map;

}
