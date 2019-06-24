/*
 * File: PigLatinUsingTokenScanner.java
 * ------------------------------------
 * This file takes a line of text and converts each word into Pig Latin.
 * The rules for forming Pig Latin words are as follows:
 *
 * o If the word begins with a vowel, add "way" to the end of the word.
 *
 * o If the word begins with a consonant, extract the set of consonants
 *   up to the first vowel, move that set of consonants to the end of
 *   the word, and add "ay".
 *
 * o If the word contains no vowels, return the original word unchanged.
 */

package edu.stanford.cs.javacs2.ch7;

import java.util.Scanner;

public class PigLatinUsingTokenScanner {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("Pig Latin Translator");
      System.out.print("Enter a line: ");
      String line = sysin.nextLine();
      System.out.println(lineToPigLatin(line));
   }

/*
 * Translates a line to Pig Latin, word by word.
 */

   private String lineToPigLatin(String line) {
      TokenScanner scanner = new TokenScanner(line);
      String result = "";
      while (scanner.hasMoreTokens()) {
         String word = scanner.nextToken();
         if (Character.isLetter(word.charAt(0))) {
            word = wordToPigLatin(word);
         }
         result += word;
      }
      return result;
   }

/*
 * Translates a word to Pig Latin and returns the translated word.
 */

   private String wordToPigLatin(String word) {
      int vp = findFirstVowel(word);
      if (vp == -1) {
         return word;
      } else if (vp == 0) {
         return word + "way";
      } else {
         String head = word.substring(0, vp);
         String tail = word.substring(vp);
         return tail + head + "ay";
      }
   }

/*
 * Returns the index of the first vowel in the word, or -1 if none exist.
 */

   private int findFirstVowel(String word) {
      for (int i = 0; i < word.length(); i++) {
         if (isEnglishVowel(word.charAt(i))) return i;
      }
      return -1;
   }

/*
 * Returns true if the character is a vowel.
 */

   private boolean isEnglishVowel(char ch) {
      switch (ch) {
       case 'A': case 'E': case 'I': case 'O': case 'U':
       case 'a': case 'e': case 'i': case 'o': case 'u':
         return true;
       default:
         return false;
      }
   }

/* Main program */

   public static void main(String[] args) {
      new PigLatinUsingTokenScanner().run();
   }

}
