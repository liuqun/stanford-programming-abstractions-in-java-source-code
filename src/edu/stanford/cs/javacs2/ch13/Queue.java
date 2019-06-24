/*
 * File: Queue.java
 * ----------------
 * This file defines the interface for a class that implements a queue,
 * which is characterized by first-in/first-out (FIFO) behavior.
 */

package edu.stanford.cs.javacs2.ch13;

public interface Queue<T> {

/**
 * Returns the number of values in this queue.
 */

   public int size();

/**
 * Returns true if this queue contains no elements.
 */

   public boolean isEmpty();

/**
 * Removes all elements from this queue.
 */

   public void clear();

/**
 * Adds the specified value to the tail of this queue.
 */

   public void add(T value);

/**
 * Removes the first element from this queue and returns it.  This method
 * throws a NoSuchElementException if called on an empty queue.
 */

   public T remove();

/**
 * Returns the value of the first element in this queue without removing it.
 * This method throws a NoSuchElementException if called on an empty queue.
 */

   public T peek();

}
