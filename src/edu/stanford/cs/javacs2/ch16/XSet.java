/*
 * File: XSet.java
 * ---------------
 * This file exports the extended XSet class.
 */

package edu.stanford.cs.javacs2.ch16;

import java.util.Iterator;

public class XSet<T extends Comparable<? super T>> extends TreeSet<T>
                    implements Comparable<XSet<T>> {

/**
 * Creates an empty XSet.
 */

   public XSet() {
      /* Empty */
   }

/**
 * Creates an XSet containing the values supplied as arguments.
 */

   @SuppressWarnings("unchecked")
   public XSet(T... args) {
      for (T value : args) {
         add(value);
      }
   }

/**
 * Creates an XSet containing the values from the argument set.
 */

   public XSet(Set<T> set) {
      for (T value : set) {
         add(value);
      }
   }

/**
 * Converts the set to its string representation.
 */

   @Override
   public String toString() {
      String str = "";
      for (T value : this) {
         if (!str.isEmpty()) str += ", ";
         str += value.toString();
      }
      return "{" + str + "}";
   }

/**
 * Creates a new set that is the union of this set and s2.
 */

   public XSet<T> union(Set<T> s2) {
      XSet<T> result = new XSet<T>();
      for (T value : this) {
         result.add(value);
      }
      for (T value : s2) {
         result.add(value);
      }
      return result;
   }

/**
 * Creates a new set that is the intersection of this set and s2.
 */

   public XSet<T> intersect(Set<T> s2) {
      XSet<T> result = new XSet<T>();
      for (T value : this) {
         if (s2.contains(value)) result.add(value);
      }
      return result;
   }

/**
 * Creates a new set that is the set difference of this set and s2.
 */

   public XSet<T> subtract(Set<T> s2) {
      XSet<T> result = new XSet<T>();
      for (T value : this) {
         if (!s2.contains(value)) result.add(value);
      }
      return result;
   }

/**
 * Returns true if this set is a subset of s2.
 */

   public boolean isSubsetOf(Set<T> s2) {
      for (T value : this) {
         if (!s2.contains(value)) return false;
      }
      return true;
   }

/**
 * Returns true if obj is an XSet and this set is equal to obj.
 */

   @Override
   @SuppressWarnings("unchecked")
   public boolean equals(Object obj) {
      try {
         XSet<T> s2 = (XSet<T>) obj;
         return s2.isSubsetOf(this) && this.isSubsetOf(s2);
      } catch (ClassCastException ex) {
         return false;
      }
   }

/**
 * Returns a hash code based on the elements of the set.
 */

   @Override
   public int hashCode() {
      int hc = 0;
      for (T value : this) {
         hc += value.hashCode();
      }
      return hc;
   }

/**
 * Compares this set to the set s2, returning a negative value if this
 * set is less than s2, a positive value if this set is greater than s2,
 * and 0 if the two sets contain the same elements.  Sets are compared
 * first by their length and then by their elements in iterator order.
 */

   public int compareTo(XSet<T> s2) {
      int cmp = this.size() - s2.size();
      if (cmp != 0) return cmp;
      Iterator<T> it1 = this.iterator();
      Iterator<T> it2 = s2.iterator();
      while (it1.hasNext() || it2.hasNext()) {
         T v1 = it1.next();
         T v2 = it2.next();
         cmp = v1.compareTo(v2);
         if (cmp != 0) return cmp;
      }
      if (it1.hasNext()) return +1;
      if (it2.hasNext()) return -1;
      return 0;
   }

}
