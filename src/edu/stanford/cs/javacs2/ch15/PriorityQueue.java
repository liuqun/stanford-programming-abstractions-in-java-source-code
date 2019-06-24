/*
 * File: PriorityQueue.java
 * ------------------------
 * This file implements the priority queue abstraction using a heap to
 * represent a partially ordered tree in which every node is smaller
 * than either of its children.  Maintaining this property during add
 * and remove operations requires log N time.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.javacs2.ch13.GenericArray;
import java.util.NoSuchElementException;

/*
 * This class implements a queue structure whose elements are removed in
 * priority order.  As in conventional English usage, lower priority values
 * are removed first.  Thus, priority 1 items come before priority 2.
 */

public class PriorityQueue<T extends Comparable<? super T>> {

/**
 * Creates an empty priority queue.
 */

   public PriorityQueue() {
      capacity = INITIAL_CAPACITY;
      array = new GenericArray<T>(capacity);
      count = 0;
   }

/**
 * Returns the number of values in this queue.
 */

   public int size() {
      return count;
   }

/**
 * Returns true if this queue contains no elements.
 */

   public boolean isEmpty() {
      return count == 0;
   }

/**
 * Removes all elements from this queue.
 */

   public void clear() {
      count = 0;
   }

/**
 * Adds the specified value to this queue.
 */

   public void add(T value) {
      if (count == capacity) expandCapacity();
      int index = count++;
      array.set(index, value);
      while (index > 0) {
         int parent = (index - 1) / 2;
         if (array.get(parent).compareTo(array.get(index)) < 0) break;
         swapHeapEntries(parent, index);
         index = parent;
      }
   }

/**
 * Removes the first element from this queue and returns it.  This method
 * throws a NoSuchElementException if called on an empty queue.
 */

   public T remove() {
      if (count == 0) throw new NoSuchElementException("Queue is empty");
      T value = array.get(0);
      swapHeapEntries(0, --count);
      int index = 0;
      while (true) {
         int left = 2 * index + 1;
         int right = 2 * index + 2;
         if (left >= count) break;
         int child = left;
         if (right < count &&
             array.get(right).compareTo(array.get(left)) < 0) child = right;
         if (array.get(index).compareTo(array.get(child)) < 0) break;
         swapHeapEntries(index, child);
         index = child;
      }
      return value;
   }

/**
 * Returns the value of the first element in this queue without removing it.
 * This method throws a NoSuchElementException if called on an empty queue.
 */

   public T peek() {
      if (count == 0) throw new NoSuchElementException("Queue is empty");
      return array.get(0);
   }

/*
 * Swaps two elements in the heap.
 */

   private void swapHeapEntries(int i1, int i2) {
      T tmp = array.get(i1);
      array.set(i1, array.get(i2));
      array.set(i2, tmp);
   }

/*
 * Implementation notes: expandCapacity
 * ------------------------------------
 * The expandCapacity method allocates a new array of twice the previous
 * size, copies the old elements to the new array, and then replaces the
 * old array with the new one.
 */

   private void expandCapacity() {
      capacity *= 2;
      GenericArray<T> newArray = new GenericArray<T>(capacity);
      for (int i = 0; i < count; i++) {
         newArray.set(i, array.get(i));
      }
      array = newArray;
   }

/* Constants */

   private static final int INITIAL_CAPACITY = 10;

/*
 * Private instance variables
 * --------------------------
 * This implementation uses the generic array to store the elements in
 * a heap, which is an array-based structure that simulates a partially
 * ordered tree.  If k is the current index, the parent appears at
 * (k - 1) / 2, the left child is at 2 * k + 1, and the right child is
 * at 2 * k + 2.
 */

   private GenericArray<T> array;
   private int capacity;
   private int count;

}
