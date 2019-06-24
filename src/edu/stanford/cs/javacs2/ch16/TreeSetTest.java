/*
 * File: TreeSetTest.java
 * ----------------------
 * This program tests the tree implementation of the Set interface.
 */

package edu.stanford.cs.javacs2.ch16;

public class TreeSetTest extends SetTest {

   @Override
   public Set<String> createSet() {
      return new TreeSet<String>();
   }

   public static void main(String[] args) {
      new TreeSetTest().run();
   }

}
