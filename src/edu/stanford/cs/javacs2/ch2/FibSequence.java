/*
 * File: FibSequence.java
 * ----------------------
 * This program lists the terms in the Fibonacci sequence with
 * indices ranging from LOWER_LIMIT to UPPER_LIMIT.  This version
 * defines a more general additiveSequence method that avoids
 * repeated computation.
 */

package edu.stanford.cs.javacs2.ch2;

public class FibSequence {

   public void run() {
      for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++) {
         System.out.println("fib(" + i + ") = " + fib(i));
      }
   }

/*
 * Returns the nth term in the Fibonacci sequence.  This version
 * uses a helper method for computing additive sequences that
 * avoids unnecessary computation.
 */

   private int fib(int n) {
      return additiveSequence(n, 0, 1);
   }

/*
 * Returns the nth term in a general additive sequence in which
 *
 *     t(n) = t(n - 1) + t(n)
 *
 * and the first two terms are t0 and t1.  This implementation is
 * more efficient than the most straightforward recursive strategy
 * and makes use of the fact that the nth term in an additive
 * sequence beginning at t0 and t1 is the (n - 1)st term in a
 * sequence beginning at t1 and t0 + t1.
 */

   private int additiveSequence(int n, int t0, int t1) {
      if (n == 0) return t0;
      if (n == 1) return t1;
      return additiveSequence(n - 1, t1, t0 + t1);
   }

/* Constants */

   private static final int LOWER_LIMIT = 0;
   private static final int UPPER_LIMIT = 20;

/* Main program */

   public static void main(String[] args) {
      new FibSequence().run();
   }

}
