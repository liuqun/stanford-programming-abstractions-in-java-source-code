/*
 * File: Collection.java
 * ---------------------
 * This interface defines the operations shared by sets and lists.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.Iterator;

public interface Collection<T> extends Iterable<T> {

/**
 * Returns the number of values in this collection.
 */

   public int size();

/**
 * Returns true if this collection contains no elements.
 */

   public boolean isEmpty();

/**
 * Removes all elements from this collection.
 */

   public void clear();

/**
 * Adds the specified value to this collection.
 */

   public void add(T value);

/**
 * Removes the element with the specified value.
 */

   public void remove(T value);

/**
 * Returns true if the collection contains the specified value.
 */

   public boolean contains(T value);

/**
 * Returns an iterator for this collection.
 */

   public Iterator<T> iterator();

}
