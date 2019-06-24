/*
 * File: IsLeapYear.java
 * ---------------------
 * This program implements an interactive test of the isLeapYear function.
 */

package edu.stanford.cs.javacs2.ch2;

import java.util.Scanner;

public class IsLeapYear {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program tests the isLeapYear function.");
      while (true) {
         System.out.print("Enter a year: ");
         int year = sysin.nextInt();
         if (year <= 0) break;
         System.out.println("isLeapYear(" + year + ") -> " + isLeapYear(year));
      }
   }

/*
 * Tests to see whether the specified year is a leap year.  Leap years
 * are those divisible by 4, with the exception of century years, which
 * are leap years only if they are divisible by 400.  Thus, 1900 was not
 * a leap year, but 2000 was.
 */

   private boolean isLeapYear(int year) {
      if (year % 100 == 0) {
         return year % 400 == 0;
      } else {
         return year % 4 == 0;
      }
   }

/* Main program */

   public static void main(String[] args) {
      new IsLeapYear().run();
   }

}
