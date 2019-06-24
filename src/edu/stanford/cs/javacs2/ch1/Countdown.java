/*
 * File: Countdown.java
 * --------------------
 * This program counts backwards from the value START to zero.
 */

package edu.stanford.cs.javacs2.ch1;

public class Countdown {

   public void run() {
      for (int t = START; t >= 0; t--) {
         System.out.println(t);
      }
   }

/* Constants */

   private static final int START = 10;

/* Main program */

   public static void main(String[] args) {
      new Countdown().run();
   }

}
