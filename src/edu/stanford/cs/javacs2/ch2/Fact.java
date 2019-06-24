/*
 * File: Fact.java
 * ---------------
 * This program generates a list of factorials using the standard
 * recursive formulation.
 */

package edu.stanford.cs.javacs2.ch2;

public class Fact {

   public void run() {
      for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++) {
         System.out.println(i + "! = " + fact(i));
      }
   }

/*
 * Computes the factorial of n using the following recursive formulation:
 *
 *    fact(0) = 1
 *    fact(n) = n * fact(n - 1)
 */

   private int fact(int n) {
      if (n == 0) {
         return 1;
      } else {
         return n * fact(n - 1);
      }
   }

/* Private constants */

   private static final int LOWER_LIMIT = 0;
   private static final int UPPER_LIMIT = 12;

/* Main program */

   public static void main(String[] args) {
      new Fact().run();
   }

}
