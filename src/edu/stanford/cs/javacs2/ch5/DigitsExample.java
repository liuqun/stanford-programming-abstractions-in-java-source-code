/*
 * File: DigitsExample.java
 * ------------------------
 * This program makes sure that the array initialization code used in
 * the chapter works correctly.
 */

package edu.stanford.cs.javacs2.ch5;

public class DigitsExample {

   public void run() {
      System.out.println("DIGITS = " + arrayToString(DIGITS));
   }

   private String arrayToString(int[] array) {
      String str = "";
      for (int i = 0; i < array.length; i++) {
         if (i > 0) str += ", ";
         str += array[i];
      }
      return "[" + str + "]";
   }

/* Constants */

   private static final int[] DIGITS = {
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9
   };

/* Main program */

   public static void main(String[] args) {
      new DigitsExample().run();
   }

}
