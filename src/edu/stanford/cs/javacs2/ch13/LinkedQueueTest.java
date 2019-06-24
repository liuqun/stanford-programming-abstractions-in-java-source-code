/*
 * File: LinkedQueueTest.java
 * --------------------------
 * This program tests the linked-list implementation of the Queue interface.
 */

package edu.stanford.cs.javacs2.ch13;

public class LinkedQueueTest extends QueueTest {

   @Override
   public Queue<String> createQueue() {
      return new LinkedQueue<String>();
   }

   public static void main(String[] args) {
      new LinkedQueueTest().run();
   }

}
