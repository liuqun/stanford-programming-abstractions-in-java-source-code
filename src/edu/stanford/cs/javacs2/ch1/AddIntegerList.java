/*
 * File: AddIntegerList.java
 * -------------------------
 * This program adds a list of integers.  The end of the input is indicated
 * by entering a sentinel value, which is defined by the constant SENTINEL.
 */

package edu.stanford.cs.javacs2.ch1;

import java.util.Scanner;

public class AddIntegerList {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program adds a list of integers.");
      System.out.println("Use " + SENTINEL + " to signal the end.");
      int total = 0;
      while (true) {
         System.out.print(" ? ");
         int value = sysin.nextInt();
         if (value == SENTINEL) break;
         total += value;
      }
      System.out.println("The total is " + total);
   }

/* Private constants */

   private static final int SENTINEL = 0;

/* Main program */

   public static void main(String[] args) {
      new AddIntegerList().run();
   }

}
