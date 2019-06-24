/*
 * File: HashStringMapTest.java
 * ----------------------------
 * This program implements an interactive test of the StringMap interface.
 */

package edu.stanford.cs.javacs2.ch14;

public class HashStringMapTest extends StringMapTest {

   @Override
   public StringMap createMap() {
      return new HashStringMap();
   }

   public static void main(String[] args) {
      new HashStringMapTest().run();
   }

}
