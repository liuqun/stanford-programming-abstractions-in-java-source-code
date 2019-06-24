/*
 * File: Combinations.java
 * -----------------------
 * This program computes the mathematical function C(n, k) from
 * its mathematical definition in terms of factorials.
 */

package edu.stanford.cs.javacs2.ch2;

import java.util.Scanner;

public class Combinations {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.print("Enter the number of objects (n): ");
      int n = sysin.nextInt();
      System.out.print("Enter the number to be chosen (k): ");
      int k = sysin.nextInt();
      System.out.println("C(n, k) = " + combinations(n, k));
   }

/*
 * Returns the mathematical combinations function C(n, k), which is
 * the number of ways one can choose k elements from a set of size n.
 */

   private int combinations(int n, int k) {
      return fact(n) / (fact(k) * fact(n - k));
   }

/*
 * Returns the factorial of n, which is the product of all the
 * integers between 1 and n, inclusive.
 */

   private int fact(int n) {
      int result = 1;
      for (int i = 1; i <= n; i++) {
         result *= i;
      }
      return result;
   }

/* Main program */

   public static void main(String[] args) {
      new Combinations().run();
   }

}
