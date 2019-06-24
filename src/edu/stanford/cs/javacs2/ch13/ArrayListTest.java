/*
 * File: ArrayListTest.java
 * ------------------------
 * This program tests the array implementation of the List interface.
 */

package edu.stanford.cs.javacs2.ch13;

public class ArrayListTest extends ListTest {

   public static void main(String[] args) {
      new ArrayListTest().run();
   }

   @Override
   public List<String> createList() {
      return new ArrayList<String>();
   }

}
