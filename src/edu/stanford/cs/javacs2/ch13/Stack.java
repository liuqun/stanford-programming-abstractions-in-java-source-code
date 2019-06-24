/*
 * File: Stack.java
 * ----------------
 * This file simulates the Stack class from the java.util package.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.NoSuchElementException;

public class Stack<T> {

/**
 * Creates a new empty stack.
 */

   public Stack() {
      capacity = INITIAL_CAPACITY;
      array = new GenericArray<T>(capacity);
      count = 0;
   }

   public int size() {
      return count;
   }

   public boolean isEmpty() {
      return count == 0;
   }

   public void clear() {
      count = 0;
   }

/*
 * Implementation notes: push and pop
 * ----------------------------------
 * These methods manipulate the contents of the underlying array.  The push
 * method checks the capacity; pop checks for an empty stack.
 */

   public void push(T value) {
      if (count == capacity) expandCapacity();
      array.set(count++, value);
   }

   public T pop() {
      if (count == 0) throw new NoSuchElementException("Stack is empty");
      return array.get(--count);
   }

   public T peek() {
      if (count == 0) throw new NoSuchElementException("Stack is empty");
      return array.get(count - 1);
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
 * The elements in a Stack are stored in a GenericArray, which is necessary
 * to get around Java's prohibition on arrays of a generic type.
 */

   private GenericArray<T> array;  /* Array of elements in the stack   */
   private int capacity;           /* Allocated capacity of the array  */
   private int count;              /* Actual number of elements in use */

}
