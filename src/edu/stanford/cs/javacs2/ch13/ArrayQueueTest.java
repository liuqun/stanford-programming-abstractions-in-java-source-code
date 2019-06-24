/*
 * File: ArrayQueueTest.java
 * -------------------------
 * This program tests the array implementation of the Queue interface.
 */

package edu.stanford.cs.javacs2.ch13;

public class ArrayQueueTest extends QueueTest {

   @Override
   public Queue<String> createQueue() {
      return new ArrayQueue<String>();
   }

   public static void main(String[] args) {
      new ArrayQueueTest().run();
   }

}
