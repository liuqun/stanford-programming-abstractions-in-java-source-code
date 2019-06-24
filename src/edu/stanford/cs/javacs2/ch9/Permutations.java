/*
 * File: Permutations.java
 * -----------------------
 * This file generates all permutations of an input string.
 */

package edu.stanford.cs.javacs2.ch9;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import java.util.TreeSet;

public class Permutations {

   public void run() {
      Console console = new SystemConsole();
      String str = console.nextLine("Enter a string: ");
      System.out.println("The permutations of " + str + " are:");
      for (String s : generatePermutations(str)) {
         System.out.println("  " + s);
      }
   }

/*
 * Returns a set consisting of all permutations of the specified string.
 * This implementation uses the recursive insight that you can generate
 * all permutations of a string by selecting each character, generating
 * all permutations of the string without that character, and then
 * concatenating the selected character on the front of each string.
 */

   private TreeSet<String> generatePermutations(String str) {
      TreeSet<String> result = new TreeSet<String>();
      if (str.equals("")) {
         result.add("");
      } else {
         for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String rest = str.substring(0, i) + str.substring(i + 1);
            for (String s : generatePermutations(rest)) {
               result.add(ch + s);
            }
         }
      }
      return result;
   }

/* Main program */

   public static void main(String[] args) {
      new Permutations().run();
   }

}
