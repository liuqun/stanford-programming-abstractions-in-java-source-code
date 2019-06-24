/*
 * File: LinkedStackTest.java
 * --------------------------
 * This program tests the linked-list implementation of the Stack interface.
 */

package edu.stanford.cs.javacs2.ch13;

public class LinkedStackTest extends StackTest {

   @Override
   public Stack<String> createStack() {
      return new Stack<String>();
   }

   public static void main(String[] args) {
      new LinkedStackTest().run();
   }

}
