/*
 * File: FactorialTable.java
 * -------------------------
 * This file generates a table of factorials.
 */

package edu.stanford.cs.javacs2.ch2;

public class FactorialTable {

   public void run() {
      for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++) {
         System.out.println(i + "! = " + fact(i));
      }
   }

/*
 * Returns the factorial of n, which is defined as the product of
 * all integers from 1 up to n.
 */

   private int fact(int n) {
      int result = 1;
      for (int i = 1; i <= n; i++) {
         result *= i;
      }
      return result;
   }

/* Private constants */

   private static final int LOWER_LIMIT = 0;
   private static final int UPPER_LIMIT = 12;

/* Main program */

   public static void main(String[] args) {
      new FactorialTable().run();
   }

}
