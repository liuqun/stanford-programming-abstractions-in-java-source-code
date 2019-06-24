/*
 * File: LexiconSubclass.java
 * --------------------------
 * This file implements the additional methods required for the Lexicon
 * class by extending TreeSet<String>.
 */

package edu.stanford.cs.javacs2.ch8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class LexiconSubclass extends TreeSet<String> {

   public LexiconSubclass(String filename) {
      try {
         BufferedReader rd = new BufferedReader(new FileReader(filename));
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            add(line);
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 *
/**
 * Returns true if the specified string is a valid prefix of some word
 * in the lexicon.
 *
 * @param prefix The prefix string being tested
 * @return The value true if the string is a valid prefix
 */

   public boolean containsPrefix(String prefix) {
      String prev = ceiling(prefix);
      return prev != null && prev.startsWith(prefix);
   }

/* Main program to test the LexiconSubclass abstraction */

   public static void main(String[] args) {
      LexiconSubclass english = new LexiconSubclass("EnglishWords.txt");
      int twoCount = 0;
      String first = null;
      String last = null;
      for (String word : english) {
         if (first == null) first = word;
         last = word;
         if (word.length() == 2) twoCount++;
      }
      System.out.println("The lexicon contains " + english.size() + " words.");
      System.out.println("The first word is " + first + ".");
      System.out.println("The last word is " + last + ".");
      System.out.println("There are " + twoCount + " two-letter words.");
      System.out.println("english.contains(\"xyl\") -> "
                        + english.contains("xyl"));
      System.out.println("english.containsPrefix(\"xyl\") -> "
                        + english.containsPrefix("xyl"));
      System.out.println("english.contains(\"xylem\") -> "
                        + english.contains("xylem"));
      System.out.println("english.containsPrefix(\"xylem\") -> "
                        + english.containsPrefix("xylem"));
      System.out.println("english.contains(\"xyzzy\") -> "
                        + english.contains("xyzzy"));
      System.out.println("english.containsPrefix(\"xyzzy\") -> "
                        + english.containsPrefix("xyzzy"));
   }

}
