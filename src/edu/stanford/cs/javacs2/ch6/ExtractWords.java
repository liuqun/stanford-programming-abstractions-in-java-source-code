/*
 * File: ExtractWords.java
 * -----------------------
 * This program defines and tests the function extractWords, which
 * extracts all consecutive sequences of letters from a String and
 * stores them in a ArrayList<String>.
 */

package edu.stanford.cs.javacs2.ch6;

import java.util.ArrayList;
import java.util.Scanner;

public class ExtractWords {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program tests the extractWords function.");
      while (true) {
         System.out.print("Enter a line of text: ");
         String line = sysin.nextLine();
         if (line.equals("")) break;
         ArrayList<String> words = extractWords(line);
         System.out.println("The words in that line are:");
         for (int i = 0; i < words.size(); i++) {
            System.out.println("  \"" + words.get(i) + "\"");
         }
      }
   }

/**
 * Scans through the characters in the line and stores each individual
 * word (any sequence of letters) in an ArrayList
 * of strings, which is returned as the value of the function.
 */

   private ArrayList<String> extractWords(String line) {
      ArrayList<String> words = new ArrayList<String>();
      int start = -1;
      for (int i = 0; i < line.length(); i++) {
         char ch = line.charAt(i);
         if (Character.isLetter(ch)) {
            if (start == -1) start = i;
         } else {
            if (start >= 0) {
               words.add(line.substring(start, i));
               start = -1;
            }
         }
      }
      if (start >= 0) words.add(line.substring(start));
      return words;
   }

/* Main program */

   public static void main(String[] args) {
      new ExtractWords().run();
   }

}
