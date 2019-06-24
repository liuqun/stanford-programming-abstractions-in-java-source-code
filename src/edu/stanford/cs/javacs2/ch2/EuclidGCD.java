/*
 * File: EuclidGCD.java
 * --------------------
 * This program uses Euclid's algorithm to compute the greatest
 * common divisor of two integers.
 */

package edu.stanford.cs.javacs2.ch2;

import java.util.Scanner;

public class EuclidGCD {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program computes the gcd of x and y.");
      System.out.print("Enter x: ");
      int x = sysin.nextInt();
      System.out.print("Enter y: ");
      int y = sysin.nextInt();
      System.out.println("gcd(" + x + ", " + y + ") = " + gcd(x, y));
   }

/*
 * Returns the greatest common divisor (gcd) of the two integers,
 * x and y.  This implementation of the gcd method uses Euclid's
 * algorithm.
 */
   private int gcd(int x, int y) {
      int r = x % y;
      while (r != 0) {
         x = y;
         y = r;
         r = x % y;
      }
      return y;
   }

/* Main program */

   public static void main(String[] args) {
      new EuclidGCD().run();
   }

}
