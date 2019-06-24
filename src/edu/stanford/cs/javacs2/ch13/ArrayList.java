/*
 * File: ArrayList.java
 * --------------------
 * Implements the List interface using a GenericArray as the underlying
 * data structure.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

   public ArrayList() {
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

   public void add(T value) {
      if (count == capacity) expandCapacity();
      array.set(count++, value);
   }

   public void add(int k, T value) {
      if (k < 0 || k > size()) {
         throw new IndexOutOfBoundsException("Index " + k + " out of bounds");
      }
      if (count == capacity) expandCapacity();
      for (int i = count; i > k; i--) {
         array.set(i, array.get(i - 1));
      }
      array.set(k, value);
      count++;
   }

   public void remove(int k) {
      if (k < 0 || k >= size()) {
         throw new IndexOutOfBoundsException("Index " + k + " out of bounds");
      }
      for (int i = k + 1; i < count; i++) {
         array.set(i - 1, array.get(i));
      }
      count--;
   }

   public void remove(T value) {
      remove(indexOf(value));
   }

   public boolean contains(T value) {
      return indexOf(value) != -1;
   }

   public int indexOf(T value) {
      for (int i = 0; i < count; i++) {
         if (array.get(i).equals(value)) return i;
      }
      return -1;
   }

   public T get(int k) {
      if (k < 0 || k >= count) {
         throw new IndexOutOfBoundsException("index " + k + " out of bounds");
      }
      return array.get(k);
   }

   public void set(int k, T value) {
      if (k < 0 || k >= count) {
         throw new IndexOutOfBoundsException("index " + k + " out of bounds");
      }
      array.set(k, value);
   }

   @Override
   public String toString() {
      String str = "";
      for (int i = 0; i < count; i++) {
         if (i > 0) str += ", ";
         str += array.get(i);
      }
      return "[" + str + "]";
   }

   public Iterator<T> iterator() {
      return new ArrayListIterator();
   }

/*
 * Implementation notes: expandCapacity
 * ------------------------------------
 * This private method doubles the size of the array whenever the old one
 * runs out of space.  To do so, expandCapacity allocates a new array,
 * copies the old elements to the new array, and then replaces the old
 * array with the new one.
 */

   private void expandCapacity() {
      capacity *= 2;
      GenericArray<T> newArray = new GenericArray<T>(capacity);
      for (int i = 0; i < count; i++) {
         newArray.set(i, array.get(i));
      }
      array = newArray;
   }

/* Implements an iterator for the ArrayList class */

   private class ArrayListIterator implements Iterator<T> {

   /* Creates a new iterator for this ArrayList */

      public ArrayListIterator() {
         currentIndex = 0;
      }

   /* Returns true if there are more elements in the ArrayList */

      public boolean hasNext() {
         return currentIndex < count;
      }

   /* Returns the next element in the ArrayList and advances the index */

      public T next() {
         if (!hasNext()) throw new NoSuchElementException("No next element");
         return array.get(currentIndex++);
      }

   /* Unsupported operation defined by Iterator but not implemented here */

      public void remove() {
         throw new UnsupportedOperationException("remove not implemented");
      }

   /* Private instance variables */

      private int currentIndex;   /* The index of the current element */

   }

/* Constants */

   private static final int INITIAL_CAPACITY = 10;

/* Private instance variables */

   private GenericArray<T> array;
   private int capacity;
   private int count;

}
