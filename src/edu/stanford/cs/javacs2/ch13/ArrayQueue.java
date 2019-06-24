/*
 * File: ArrayQueue.java
 * ---------------------
 * This file implements the Queue abstraction using a ring buffer.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

/**
 * Creates a new empty queue.
 */

   public ArrayQueue() {
      capacity = INITIAL_CAPACITY;
      array = new GenericArray<T>(capacity);
      head = 0;
      tail = 0;
   }

   public int size() {
      return (tail + capacity - head) % capacity;
   }

   public boolean isEmpty() {
      return head == tail;
   }

   public void clear() {
      head = tail = 0;
   }

   public void add(T value) {
      if (size() == capacity - 1) expandCapacity();
      array.set(tail, value);
      tail = (tail + 1) % capacity;
   }

   public T remove() {
      if (isEmpty()) throw new NoSuchElementException("Queue is empty");
      T value = array.get(head);
      head = (head + 1) % capacity;
      return value;
   }

   public T peek() {
      if (isEmpty()) throw new NoSuchElementException("Queue is empty");
      return array.get(head);
   }

/*
 * Implementation notes: expandCapacity
 * ------------------------------------
 * This private method doubles the size of the array whenever the old one
 * runs out of space.  To do so, expandCapacity allocates a new array,
 * copies the old elements to the new array, and then replaces the old
 * array with the new one.  Note that the queue capacity is reached when
 * there is still one unused element in the array.  If the queue is allowed
 * to fill completely, the head and tail indices have the same value, and
 * the queue appears empty.
 */

   private void expandCapacity() {
      GenericArray<T> newArray = new GenericArray<T>(2 * capacity);
      int count = size();
      for (int i = 0; i < count; i++) {
         newArray.set(i, array.get((head + i) % capacity));
      }
      head = 0;
      tail = count;
      capacity *= 2;
      array = newArray;
   }

/* Constants */

   private static final int INITIAL_CAPACITY = 10;

/*
 * Private instance variables
 * --------------------------
 * In the ArrayQueue implementation, the elements are stored in successive
 * index positions in a GenericArray, just as they are in an ArrayStack.
 * What makes the queue structure more complex is the need to avoid
 * shifting elements as the queue expands and contracts.  In the array
 * model, this goal is achieved by keeping track of both the head and tail
 * indices.  The tail index increases by one each time an element is added,
 * and the head index increases by one each time an element is removed.
 * Each index therefore marches toward the end of the allocated array and
 * will eventually reach the end.  Rather than allocate new memory, this
 * implementation lets each index wrap around to the beginning as if the
 * ends of the array were joined to form a circle.  This representation
 * is called a ring buffer.
 */

   private GenericArray<T> array;  /* Array of elements in the queue   */
   private int capacity;           /* Allocated capacity of the array  */
   private int head;               /* Index of the first queue element */
   private int tail;               /* Index of the first free slot     */

}
