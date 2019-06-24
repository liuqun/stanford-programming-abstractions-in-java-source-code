/*
 * File: LinkedList.java
 * ---------------------
 * Implements the List interface using a linked list as the underlying
 * data structure.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {

   public LinkedList() {
      clear();
   }

   public int size() {
      return count;
   }

   public boolean isEmpty() {
      return count == 0;
   }

   public void clear() {
      head = tail = null;
      count = 0;
   }

   public void add(T value) {
      Cell cp = new Cell();
      cp.value = value;
      cp.link = null;
      if (head == null) {
         head = cp;
      } else {
         tail.link = cp;
      }
      tail = cp;
      count++;
   }

   public void add(int k, T value) {
      if (k < 0 || k > count) {
         throw new IndexOutOfBoundsException("Index " + k + " out of bounds");
      }
      if (k == count) {
         add(value);
      } else {
         Cell prev = null;
         Cell cp = head;
         for (int i = 0; i < k; i++) {
            prev = cp;
            cp = cp.link;
         }
         cp = new Cell();
         cp.value = value;
         if (prev == null) {
            cp.link = head;
            head = cp;
         } else {
            cp.link = prev.link;
            prev.link = cp;
         }
         count++;
      }
   }

   public void remove(int k) {
      if (k < 0 || k >= count) {
         throw new IndexOutOfBoundsException("Index " + k + " out of bounds");
      }
      Cell prev = null;
      Cell cp = head;
      for (int i = 0; i < k; i++) {
         prev = cp;
         cp = cp.link;
      }
      if (prev == null) {
         head = cp.link;
         if (head == null) tail = null;
      } else {
         prev.link = cp.link;
         if (prev.link == null) tail = prev;
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
      int i = 0;
      for (Cell cp = head; cp != null; cp = cp.link) {
         if (cp.value.equals(value)) return i;
         i++;
      }
      return -1;
   }

   public T get(int k) {
      if (k < 0 || k >= count) {
         throw new IndexOutOfBoundsException("index " + k + " out of bounds");
      }
      if (k == count) return tail.value;
      Cell cp = head;
      for (int i = 0; i < k; i++) {
         cp = cp.link;
      }
      return cp.value;
   }

   public void set(int k, T value) {
      if (k < 0 || k >= count) {
         throw new IndexOutOfBoundsException("index " + k + " out of bounds");
      }
      if (k == count) {
         tail.value = value;
      } else {
         Cell cp = head;
         for (int i = 0; i < k; i++) {
            cp = cp.link;
         }
         cp.value = value;
      }
   }

   @Override
   public String toString() {
      String str = "";
      boolean first = true;
      for (T value : this) {
         if (!first) str += ", ";
         str += value;
         first = false;
      }
      return "[" + str + "]";
   }

   public Iterator<T> iterator() {
      return new LinkedListIterator();
   }

/* Type that represents a cell in the linked list */

   private class Cell {
      T value;
      Cell link;
   }

/* Iterator implementation */

   private class LinkedListIterator implements Iterator<T> {

      public LinkedListIterator() {
         currentCell = head;
      }

      public boolean hasNext() {
         return currentCell != null;
      }

      public T next() {
         if (currentCell == null) {
            throw new NoSuchElementException("No next element");
         }
         T value = currentCell.value;
         currentCell = currentCell.link;
         return value;
      }

      public void remove() {
         throw new RuntimeException("remove: unimplemented operation");
      }

      private Cell currentCell;

   }

/*
 * Private instance variables
 * --------------------------
 * The LinkedList implementation uses the same internal structure as
 * LinkedQueue so that adding an element at the end remains fast.
 */

   private Cell head;           /* First cell in the list          */
   private Cell tail;           /* Last cell in the list           */
   private int count;           /* Number of elements in the list  */

}
