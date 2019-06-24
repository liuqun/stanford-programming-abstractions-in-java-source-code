/*
 * File: LinkedStack.java
 * ----------------------
 * This file reimplements the Stack class using a linked list.
 */

package edu.stanford.cs.javacs2.ch13;

import java.util.NoSuchElementException;

public class LinkedStack<T> extends Stack<T> {

/**
 * Creates a new empty stack.
 */

   public LinkedStack() {
      clear();
   }

/*
 * Implementation notes: size, isEmpty
 * -----------------------------------
 * These methods use the count variable and therefore run in constant time.
 */

   @Override
   public int size() {
      return count;
   }

   @Override
   public boolean isEmpty() {
      return count == 0;
   }

   @Override
   public void clear() {
      start = null;
      count = 0;
   }

/*
 * Implementation notes: push
 * --------------------------
 * This method chains a new element onto the front of the list where it
 * becomes the top of the stack.
 */

   @Override
   public void push(T value) {
      Cell cp = new Cell();
      cp.value = value;
      cp.link = start;
      start = cp;
      count++;
   }

/*
 * Implementation notes: pop, peek
 * -------------------------------
 * These methods check for an empty stack and report an error if
 * there is no top element.
 */

   @Override
   public T pop() {
      if (count == 0) throw new NoSuchElementException("Stack is empty");
      T value = start.value;
      start = start.link;
      count--;
      return value;
   }

   @Override
   public T peek() {
      if (count == 0) throw new NoSuchElementException("Stack is empty");
      return start.value;
   }

/* Inner class that represents a cell in the linked list */

   private class Cell {
      T value;
      Cell link;
   }

/*
 * Private instance variables
 * --------------------------
 * The elements in the list-based stack are stored in a singly linked
 * list in which the top of the stack is always at the front of the list.
 * Including the count field allows the size method to run in constant time.
 *
 * The following diagram illustrates the structure of a stack containing
 * three elements -- A, B, and C -- pushed in that order:
 *
 *        +-------+      +-------+      +-------+      +-------+
 *  start |   o---+----->|   C   |  +-->|   B   |  +-->|   A   |
 *        +-------+      +-------+  |   +-------+  |   +-------+
 *  count |   3   |      |   o---+--+   |   o---+--+   |  null |
 *        +-------+      +-------+      +-------+      +-------+
 */

   private Cell start;          /* First item in the linked list   */
   private int count;           /* Number of elements in the stack */

}
