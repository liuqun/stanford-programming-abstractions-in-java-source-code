/*
 * File: ArrayStringMapTest.java
 * -----------------------------
 * This program implements an interactive test of the StringMap interface.
 */

package edu.stanford.cs.javacs2.ch14;

public class ArrayStringMapTest extends StringMapTest {

   @Override
   public StringMap createMap() {
      return new ArrayStringMap();
   }

   public static void main(String[] args) {
      new ArrayStringMapTest().run();
   }

}
