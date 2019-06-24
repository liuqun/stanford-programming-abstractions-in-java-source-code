/*
 * File: HashMapTest.java
 * ----------------------
 * This program implements an interactive test of the HashMap abstraction.
 */

package edu.stanford.cs.javacs2.ch14;

public class HashMapTest extends MapTest {

   @Override
   public Map<String,String> createMap() {
      return new HashMap<String,String>();
   }

   public static void main(String[] args) {
      new HashMapTest().run();
   }

}
