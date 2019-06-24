/*
 * File: TokenScannerTest.java
 * ---------------------------
 * This file contains a test of the simplified TokenScanner class.
 */

package edu.stanford.cs.javacs2.ch7;

public class TokenScannerTest {

   public void run() {
      System.out.println("Using test string \"" + TEST1 + "\":");
      TokenScanner scanner = new TokenScanner(TEST1);
      while (scanner.hasMoreTokens()) {
         System.out.println('"' + scanner.nextToken() + '"');
      }
      System.out.println();
      System.out.println("Ignoring whitespace in \"" + TEST2 + "\":");
      scanner.setInput(TEST2);
      scanner.ignoreWhitespace();
      while (scanner.hasMoreTokens()) {
         System.out.println('"' + scanner.nextToken() + '"');
      }
   }

/* Test strings */

   private static final String TEST1 = "This is Pig Latin.";
   private static final String TEST2 = "double area = Math.PI * r * r;";

/* Main program */

   public static void main(String[] args) {
      new TokenScannerTest().run();
   }

}
