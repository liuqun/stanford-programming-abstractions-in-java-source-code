/*
 * File: CreateIntegerArray.java
 * -----------------------------
 * This program tests the createIntegerArray method from the section on
 * variadic arguments.
 */

package edu.stanford.cs.javacs2.ch5;

public class CreateIntegerArray {

   public void run() {
      System.out.println("Odd digits = " +
                         arrayToString(createIntegerArray(1, 3, 5, 7, 9)));
      System.out.println("Prime digits = " +
                         arrayToString(createIntegerArray(2, 3, 5, 7)));
      System.out.println("Empty list = " +
                         arrayToString(createIntegerArray()));
   }

/*
 * Creates an int array containing the arguments.
 */

   private int[] createIntegerArray(int... args) {
      return args;
   }

/*
 * Converts an array of int value to a string.
 */

   private String arrayToString(int[] array) {
      String str = "";
      for (int i = 0; i < array.length; i++) {
         if (!str.isEmpty()) str += ", ";
         str += Integer.toString(array[i]);
      }
      return "[" + str + "]";
   }

/* Main program */

   public static void main(String[] args) {
      new CreateIntegerArray().run();
   }

}
