/*
 * File: IsEven.java
 * -----------------
 * This program test the predicate function isEven, which checks whether
 * an integer is even.
 */

package edu.stanford.cs.javacs2.ch2;

public class IsEven {

   public void run() {
      for (int i = 0; i < 10; i++) {
         if (isEven(i)) {
            System.out.println(i + " is even");
         } else {
            System.out.println(i + " is odd");
         }
      }
   }

/*
 * Returns true if the argument value n is even and false if it is odd.
 */

   private boolean isEven(int n) {
      return n % 2 == 0;
   }

/* Main program */

   public static void main(String[] args) {
      new IsEven().run();
   }

}
