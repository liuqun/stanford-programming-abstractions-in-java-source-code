/*
 * File: IntegerListTest.java
 * --------------------------
 * This file tests the constructor for the IntegerList class.
 */

package edu.stanford.cs.javacs2.ch8;

public class IntegerListTest {

   public void run() {
      IntegerList empty = new IntegerList();
      IntegerList singleton = new IntegerList(1);
      IntegerList digits = new IntegerList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
      System.out.println("empty = " + empty);
      System.out.println("singleton = " + singleton);
      System.out.println("digits = " + digits);
   }

/* Main program */

   public static void main(String[] args) {
      new IntegerListTest().run();
   }

}
