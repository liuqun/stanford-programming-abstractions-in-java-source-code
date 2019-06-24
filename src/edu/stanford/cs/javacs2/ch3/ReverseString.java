/*
 * File: ReverseString.java
 * ------------------------
 * This program tests three different implementations of the reverse
 * method for strings.
 */

package edu.stanford.cs.javacs2.ch3;

public class ReverseString {

   public void run() {
      testReverseString("");
      testReverseString("x");
      testReverseString("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      testReverseString("stressed");
   }

/*
 * Runs a single test of reverse with all three implementations.
 */

   private void testReverseString(String str) {
      System.out.println("backwardReverse(\"" + str + "\")" +
                         " -> \"" + backwardReverse(str) + "\"");
      System.out.println("forwardReverse(\"" + str + "\")" +
                         " -> \"" + forwardReverse(str) + "\"");
      System.out.println("recursiveReverse(\"" + str + "\")" +
                         " -> \"" + recursiveReverse(str) + "\"");
   }

/*
 * Implements reverse by iterating backward through the characters
 * of a string.
 */

   private String backwardReverse(String str) {
      String result = "";
      for (int i = str.length() - 1; i >= 0; i--) {
         result += str.charAt(i);
      }
      return result;
   }

/*
 * Implements reverse by iterating forward through the characters but
 * concatenating each character at the front of the result.
 */

   private String forwardReverse(String str) {
      String result = "";
      for (int i = 0; i < str.length(); i++) {
         result = str.charAt(i) + result;
      }
      return result;
   }

/*
 * Implements reverse recursively.
 */

   private String recursiveReverse(String str) {
      if (str.isEmpty()) {
         return "";
      } else {
         return recursiveReverse(str.substring(1)) + str.charAt(0);
      }
   }

/* Main program */

   public static void main(String[] args) {
      new ReverseString().run();
   }

}
