/*
 * File: Anagrams.java
 * -------------------
 * This file generates all legal anagrams of a sequence of letters.
 */

package edu.stanford.cs.javacs2.ch9;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch8.Lexicon;
import java.util.TreeSet;

public class Anagrams {

   public void run() {
      Console console = new SystemConsole();
      Lexicon english = new Lexicon("EnglishWords.txt");
      String letters = console.nextLine("Enter the letters: ");
      System.out.println("The anagrams of " + letters + " are:");
      for (String word : generatePermutations(letters)) {
         if (english.contains(word)) {
            System.out.println("  " + word);
         }
      }
   }

/*
 * Returns a set consisting of all permutations of the specified string.
 * This implementation uses the recursive insight that you can generate
 * all permutations of a string by selecting each character in turn,
 * generating all permutations of the string without that character,
 * and then concatenating the selected character on the front of each
 * string generated.
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
      new Anagrams().run();
   }

}
