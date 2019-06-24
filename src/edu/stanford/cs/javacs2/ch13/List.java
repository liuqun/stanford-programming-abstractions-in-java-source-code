/*
 * File: List.java
 * ---------------
 * This interface extends Collection to produce an indexable list.
 */

package edu.stanford.cs.javacs2.ch13;

public interface List<T> extends Collection<T> {

/**
 * Adds the specified value before index position k.
 */

   public void add(int k, T value);

/**
 * Removes the element at index position k.
 */

   public void remove(int k);

/**
 * Returns the first index at which the value appears, or -1 if not found.
 */

   public int indexOf(T value);

/**
 * Gets the element at index position k.
 */

   public T get(int k);

/**
 * Sets the element at index position k to value.
 */

   public void set(int k, T value);

}
