/*
 * File: WrapperTest.java
 * ----------------------
 * This program runs the code example from the text that demonstrates
 * that objects and primitive values are not the same.
 */

package edu.stanford.cs.javacs2.ch6;

public class WrapperTest {

   public void run() {
      Integer x = 5;
      Integer y = new Integer(x);
      System.out.println("x == y -> " + (x == y));
      System.out.println("x < y  -> " + (x < y));
      System.out.println("x > y  -> " + (x > y));
   }

/* Main program */

   public static void main(String[] args) {
      new WrapperTest().run();
   }

}
