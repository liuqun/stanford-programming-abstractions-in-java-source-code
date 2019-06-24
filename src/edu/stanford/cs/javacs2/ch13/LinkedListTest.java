/*
 * File: LinkedListTest.java
 * -------------------------
 * This program tests the linked-list implementation of the List interface.
 */

package edu.stanford.cs.javacs2.ch13;

public class LinkedListTest extends ListTest {

   public static void main(String[] args) {
      new LinkedListTest().run();
   }

   @Override
   public List<String> createList() {
      return new LinkedList<String>();
   }

}
