/*
 * File: ToUpperCase.java
 * ----------------------
 * This program simulates the toUpperCase method from the String class.
 */

package edu.stanford.cs.javacs2.ch3;

public class ToUpperCase {

   public void run() {
      testToUpperCase("");
      testToUpperCase("x");
      testToUpperCase("1234");
      testToUpperCase("lower case");
      testToUpperCase("UPPER CASE");
      testToUpperCase("MiXeD caSE");
   }

/*
 * Runs a single test of toUpperCase
 */

   private void testToUpperCase(String str) {
      System.out.println("toUpperCase(\"" + str + "\")" +
                         " -> \"" + toUpperCase(str) + "\"");
   }

/*
 * Returns a new string in which all letters are converted to upper case.
 */

   private String toUpperCase(String str) {
      String result = "";
      for (int i = 0; i < str.length(); i++) {
         result += Character.toUpperCase(str.charAt(i));
      }
      return result;
   }

/* Main program */

   public static void main(String[] args) {
      new ToUpperCase().run();
   }

}
