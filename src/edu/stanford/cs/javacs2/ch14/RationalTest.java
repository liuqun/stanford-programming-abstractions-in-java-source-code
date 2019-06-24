/*
 * File: RationalTest.java
 * -----------------------
 * Simple test of the Rational class.
 */

package edu.stanford.cs.javacs2.ch14;

public class RationalTest {

   public void run() {
      Rational oneHalf = new Rational(1, 2);
      Rational fiveTenths = new Rational(5, 10);
      Rational tenFifths = new Rational(10, 5);
      System.out.println("oneHalf = " + oneHalf);
      System.out.println("fiveTenths = " + fiveTenths);
      System.out.println("tenFifths = " + tenFifths);
      System.out.println("oneHalf.hashCode() = " + oneHalf.hashCode());
      System.out.println("fiveTenths.hashCode() -> " + fiveTenths.hashCode());
      System.out.println("tenFifths.hashCode() -> " + tenFifths.hashCode());
      System.out.println("oneHalf == fiveTenths -> " +
                         (oneHalf == fiveTenths));
      System.out.println("oneHalf == tenFifths -> " +
                         (oneHalf == tenFifths));
      System.out.println("oneHalf.equals(fiveTenths) = " +
                          oneHalf.equals(fiveTenths));
      System.out.println("oneHalf.equals(tenFifths) = " +
                          oneHalf.equals(tenFifths));
   }

/* Main program */

   public static void main(String[] args) {
      new RationalTest().run();
   }

}
