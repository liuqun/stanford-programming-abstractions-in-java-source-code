/*
 * File: RepeatChar.java
 * ---------------------
 * This program tests the repeatChar method from the text.
 */

package edu.stanford.cs.javacs2.ch3;

public class RepeatChar {

   public void run() {
      System.out.println(repeatChar(72, '-'));
   }

/*
 * Returns a new string composed of n copies of the character ch.
 */

   private String repeatChar(int n, char ch) {
      String str = "";
      for (int i = 0; i < n; i++) {
         str += ch;
      }
      return str;
   }

/* Main program */

   public static void main(String[] args) {
      new RepeatChar().run();
   }

}
