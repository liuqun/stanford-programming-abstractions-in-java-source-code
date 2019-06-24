/*
 * File: LinkedQueue.java
 * ----------------------
 * This file implements the Queue interface using a linked list.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

/**
 * Creates a new empty queue.
 */

   public LinkedQueue() {
      head = tail = null;
      count = 0;
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

/*
 * Implementation notes: add
 * -------------------------
 * This method allocates a new list cell and chains it in at the tail of
 * the queue.  If the queue is currently empty, the new cell also becomes
 * the head of the queue.
 */

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

/*
 * Implementation notes: remove, peek
 * ----------------------------------
 * These methods check for an empty queue and report an error if there is
 * no first element.  If the queue becomes empty, the remove method sets
 * both the head and tail variables to null.
 */

   public T remove() {
      if (isEmpty()) throw new NoSuchElementException("Queue is empty");
      T value = head.value;
      head = head.link;
      if (head == null) tail = null;
      count--;
      return value;
   }

   public T peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue is empty");
      return head.value;
   }

/* Type that represents a cell in the linked list */

   private class Cell {
      T value;
      Cell link;
   }

/*
 * Private instance variables
 * --------------------------
 * The list-based queue uses a linked list to store the elements of the
 * queue.  To ensure that adding a new element to the tail of the queue
 * is fast, the data structure maintains a pointer to the last cell in
 * the queue as well as the first.  If the queue is empty, both head
 * and tail are set to null.
 *
 * The following diagram illustrates the structure of a queue containing
 * two elements, A and B.
 *
 *       +-------+        +-------+        +-------+
 *  head |   o---+------->|   A   |  +--==>|   B   |
 *       +-------+        +-------+  |  |  +-------+
 *  tail |   o---+---+    |   o---+--+  |  |  null |
 *       +-------+   |    +-------+     |  +-------+
 *                   +------------------+
 */

   private Cell head;           /* First cell in the queue         */
   private Cell tail;           /* Last cell in the queue          */
   private int count;           /* Number of elements in the queue */

}
