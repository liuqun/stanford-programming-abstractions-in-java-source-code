/*
 * File: DigitSum.java
 * -------------------
 * This program adds the digits in a number.
 */

package edu.stanford.cs.javacs2.ch1;

import java.util.Scanner;

public class DigitSum {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program sums the digits in an integer.");
      System.out.print("Enter a number: ");
      int n = sysin.nextInt();
      int sum = 0;
      while (n > 0) {
         sum += n % 10;
         n /= 10;
      }
      System.out.println("digitSum(" + n + ") = " + sum);
   }

/* Main program */

   public static void main(String[] args) {
      new DigitSum().run();
   }

}
