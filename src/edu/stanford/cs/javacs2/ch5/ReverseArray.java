/*
 * File: ReverseArray.java
 * -----------------------
 * This program reads in an array of integers, reverses the array,
 * and then prints out the reversed array.
 */

package edu.stanford.cs.javacs2.ch5;

import java.util.Scanner;

public class ReverseArray {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      int[] array = new int[N_ELEMENTS];
      System.out.println("Enter " + N_ELEMENTS + " integers, one per line.");
      readArray(sysin, array);
      reverseArray(array);
      System.out.println("That array in reverse order looks like this:");
      printArray(array);
   }

   private void readArray(Scanner sysin, int[] array) {
      for (int i = 0; i < array.length; i++) {
         array[i] = sysin.nextInt();
      }
   }

   private void reverseArray(int[] array) {
      for (int lh = 0; lh < array.length / 2; lh++) {
         int rh = array.length - lh - 1;
         int tmp = array[lh];
         array[lh] = array[rh];
         array[rh] = tmp;
      }
   }

   private void printArray(int[] array) {
      for (int i = 0; i < array.length; i++) {
         System.out.println(array[i]);
      }
   }

/* Constants */

   private static final int N_ELEMENTS = 5;

/* Main program */

   public static void main(String[] args) {
      new ReverseArray().run();
   }

}
