/*
 * File: KeyValuePairTest.java
 * ---------------------------
 * This file implements a unit test of the KeyValuePair class.
 */

package edu.stanford.cs.javacs2.ch13;

import edu.stanford.cs.unittest.UnitTest;

public class KeyValuePairTest {

   public void run() {
      KeyValuePair<String,Integer> p1 =
         new KeyValuePair<String,Integer>("one", 1);
      UnitTest.assertEquals("p1.getKey() != \"one\"", p1.getKey(), "one");
      UnitTest.assertEquals("p1.getValue() != 1", p1.getValue(), 1);
      UnitTest.assertEquals("p1.toString() failed", p1.toString(), "<one, 1>");
      KeyValuePair<Integer,Character> p2 =
         new KeyValuePair<Integer,Character>(50, 'L');
      UnitTest.assertEquals("p2.getKey() != 50", p2.getKey(), 50);
      UnitTest.assertEquals("p2.getValue() != 'L'", p2.getValue(), 'L');
      UnitTest.assertEquals("p2.toString() failed", p2.toString(), "<50, L>");
   }

/* Main program */

   public static void main(String[] args) {
      new KeyValuePairTest().run();
   }

}
