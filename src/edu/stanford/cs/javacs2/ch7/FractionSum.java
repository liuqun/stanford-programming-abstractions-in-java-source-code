/*
 * File: FractionSum.java
 * ----------------------
 * This program illustrates the problem of round-off errors in
 * floating-point calculations.
 */

package edu.stanford.cs.javacs2.ch7;

public class FractionSum {

   public void run() {
      double a = 1.0 / 2.0;
      double b = 1.0 / 3.0;
      double c = 1.0 / 6.0;
      double sum = a + b + c;
      System.out.println("1/2 + 1/3 + 1/6 = " + sum);
   }

/* Main program */

   public static void main(String[] args) {
      new FractionSum().run();
   }

}
