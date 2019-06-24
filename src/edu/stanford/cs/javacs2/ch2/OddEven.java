/*
 * File: OddEven.java
 * ------------------
 * This program uses mutual recursion to implement the functions
 * isOdd and isEven for nonnegative numbers.
 */

package edu.stanford.cs.javacs2.ch2;

public class OddEven {

   public void run() {
      for (int i = 0; i < 10; i++) {
         if (isEven(i)) {
            System.out.println(i + " is even");
         } else {
            System.out.println(i + " is odd");
         }
      }
   }

/**
 * Returns true if the number n is odd.  A number is odd
 * if it is not even.
 */

   private boolean isOdd(int n) {
      return !isEven(n);
   }

/**
 * Returns true if the number n is even.  A number is even
 * if either (1) it is zero or (2) its predecessor is odd.
 */

   private boolean isEven(int n) {
      if (n == 0) {
         return true;
      } else {
         return isOdd(n - 1);
      }
   }

/* Main program */

   public static void main(String[] args) {
      new OddEven().run();
   }

}
