/*
 * File: RationalSum.java
 * ----------------------
 * Simple test of the Rational class.
 */

package edu.stanford.cs.javacs2.ch7;

public class RationalSum {

   public void run() {
      Rational a = new Rational(1, 2);
      Rational b = new Rational(1, 3);
      Rational c = new Rational(1, 6);
      Rational sum = a.add(b).add(c);;
      System.out.println("1/2 + 1/3 + 1/6 = " + sum);
   }

/* Main program */

   public static void main(String[] args) {
      new RationalSum().run();
   }

}
