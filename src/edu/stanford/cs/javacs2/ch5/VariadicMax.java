/*
 * File: VariadicMax.java
 * ----------------------
 * This program tests the variadic implementation of the max function for
 * integers.
 */

package edu.stanford.cs.javacs2.ch5;

public class VariadicMax {

   public void run() {
      System.out.println("max(42) = " + max(42));
      System.out.println("max(15, -3) = " + max(15, -3));
      System.out.println("max(223, 251, 317, 636, 766, 607, 607) = " +
                          max(223, 251, 317, 636, 766, 607, 607));
   }

/*
 * Returns the maximum value from a list of integers.
 */

   private int max(int n1, int... args) {
      int result = n1;
      for (int n : args) {
         if (result < n) result = n;
      }
      return result;
   }

/* Main program */

   public static void main(String[] args) {
      new VariadicMax().run();
   }

}
