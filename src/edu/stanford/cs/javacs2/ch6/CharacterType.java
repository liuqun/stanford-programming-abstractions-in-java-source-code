/*
 * File: CharacterType.java
 * ------------------------
 * This program tests a set-based implementation of several static methods
 * in the Character class.
 */

package edu.stanford.cs.javacs2.ch6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CharacterType {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      while (true) {
         System.out.print("Enter one or more characters: ");
         String str = sysin.nextLine();
         if (str.equals("")) break;
         for (int i = 0; i < str.length(); i++) {
            showCharacterType(str.charAt(i));
         }
      }
   }

   public boolean isWhitespace(char ch) {
      return WHITESPACE_SET.contains(ch);
   }

   public boolean isLowerCase(char ch) {
      return LOWERCASE_SET.contains(ch);
   }

   public boolean isUpperCase(char ch) {
      return UPPERCASE_SET.contains(ch);
   }

   public boolean isLetter(char ch) {
      return LETTER_SET.contains(ch);
   }

   public boolean isDigit(char ch) {
      return DIGIT_SET.contains(ch);
   }

   public boolean isLetterOrDigit(char ch) {
      return LETTER_OR_DIGIT_SET.contains(ch);
   }

   private void showCharacterType(char ch) {
      System.out.println(" isWhitespace('" + ch + "') -> " + isWhitespace(ch));
      System.out.println(" isLowerCase('" + ch + "') -> " + isLowerCase(ch));
      System.out.println(" isUpperCase('" + ch + "') -> " + isUpperCase(ch));
      System.out.println(" isLetter('" + ch + "') -> " + isLetter(ch));
      System.out.println(" isDigit('" + ch + "') -> " + isDigit(ch));
      System.out.println(" isLetterOrDigit('" + ch + "') -> "
                         + isLetterOrDigit(ch));
      System.out.println();
   }

   private static Set<Character> chSet(String str)  {
      Set<Character> set = new HashSet<Character>();
      for (int i = 0; i < str.length(); i++) {
         set.add(str.charAt(i));
      }
      return set;
   }

/* Constant set definitions */

   private static final Set<Character> DIGIT_SET =
      chSet("0123456789");
   private static final Set<Character> LOWERCASE_SET =
      chSet("abcdefghijklmnopqrstuvwxyz");
   private static final Set<Character> UPPERCASE_SET =
      chSet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
   private static final Set<Character> WHITESPACE_SET =
      chSet(" \t\f\n\r");
   private static final Set<Character> LETTER_SET =
      chSet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
   private static final Set<Character> LETTER_OR_DIGIT_SET =
      chSet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");

/* Main program */

   public static void main(String[] args) {
      new CharacterType().run();
   }

}
