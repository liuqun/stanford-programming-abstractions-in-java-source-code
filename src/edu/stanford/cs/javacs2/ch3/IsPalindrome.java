/*
 * File: IsPalindrome.java
 * -----------------------
 * This program tests three different implementations of the isPalindrome
 * method.
 */

package edu.stanford.cs.javacs2.ch3;

public class IsPalindrome {

   public void run() {
      testIsPalindrome("");
      testIsPalindrome("x");
      testIsPalindrome("level");
      testIsPalindrome("noon");
      testIsPalindrome("abcdefghgfedcba");
      testIsPalindrome("0123443210");
      testIsPalindrome("xyzzy");
   }

/*
 * Runs a single test of reverse with all three implementations.
 */

   private void testIsPalindrome(String str) {
      System.out.println("iterativeIsPalindrome(\"" + str + "\")" +
                         " -> " + iterativeIsPalindrome(str));
      System.out.println("recursiveIsPalindrome(\"" + str + "\")" +
                         " -> " + recursiveIsPalindrome(str));
      System.out.println("definitionalIsPalindrome(\"" + str + "\")" +
                         " -> " + definitionalIsPalindrome(str));
   }

/*
 * Implements isPalindrome by comparing characters from each end.
 */

   private boolean iterativeIsPalindrome(String str) {
      int n = str.length();
      for (int i = 0; i < n / 2; i++) {
         if (str.charAt(i) != str.charAt(n - i - 1)) {
            return false;
         }
      }
      return true;
   }

/*
 * Implements isPalindrome recursively.
 */

   private boolean recursiveIsPalindrome(String str) {
      int n = str.length();
      if (n <= 1) {
         return true;
      } else {
         return str.charAt(0) == str.charAt(n - 1) &&
                recursiveIsPalindrome(str.substring(1, n - 1));
      }
   }

/*
 * Implements isPalindrome by encoding the English definition.
 */

   private boolean definitionalIsPalindrome(String str) {
      return str.equals(reverse(str));
   }

/*
 * Implements reverse recursively.
 */

   private String reverse(String str) {
      if (str.isEmpty()) {
         return "";
      } else {
         return reverse(str.substring(1)) + str.charAt(0);
      }
   }

/* Main program */

   public static void main(String[] args) {
      new IsPalindrome().run();
   }

}
