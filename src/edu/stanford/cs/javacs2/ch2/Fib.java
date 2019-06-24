/*
 * File: Fib.java
 * --------------
 * This program lists the terms in the Fibonacci sequence with
 * indices ranging from LOWER_LIMIT to UPPER_LIMIT.
 */

package edu.stanford.cs.javacs2.ch2;

public class Fib {

   public void run() {
      for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++) {
         System.out.println("fib(" + i + ") = " + fib(i));
      }
   }

/*
 * Returns the nth term in the Fibonacci sequence using the
 * following recursive formulation:
 *
 *    fib(0) = 0
 *    fib(1) = 1
 *    fib(n) = fib(n - 1) + fib(n - 2)
 */

   private int fib(int n) {
      if (n < 2) {
         return n;
      } else {
         return fib(n - 1) + fib(n - 2);
      }
   }

/* Private constants */

   private static final int LOWER_LIMIT = 0;
   private static final int UPPER_LIMIT = 20;

/* Main program */

   public static void main(String[] args) {
      new Fib().run();
   }

}
