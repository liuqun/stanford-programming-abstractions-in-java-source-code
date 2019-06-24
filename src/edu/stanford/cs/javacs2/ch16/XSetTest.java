/*
 * File: XSetTest.java
 * -------------------
 * This program runs an interactive test of the XSet class.
 */

package edu.stanford.cs.javacs2.ch16;

public class XSetTest extends SetTest {

   @Override
   public Set<String> createSet() {
      return new XSet<String>();
   }

   public static void main(String[] args) {
      new XSetTest().run();
   }

}
