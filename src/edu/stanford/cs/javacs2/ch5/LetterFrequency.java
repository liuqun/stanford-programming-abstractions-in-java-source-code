/*
 * File: LetterFrequency.java
 * --------------------------
 * This program counts the frequency of letters in a data file.
 */

package edu.stanford.cs.javacs2.ch5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LetterFrequency {

/*
 * This program opens a file specified by the user and then counts
 * the number of times each of the 26 letters appears, keeping track
 * of those counts in an array with 26 elements.
 */

   public void run() {
      Scanner sysin = new Scanner(System.in);
      int[] letterCounts = new int[26];
      BufferedReader rd = openFileReader(sysin, "Input file: ");
      try {
         while (true) {
            int ch = rd.read();
            if (ch == -1) break;
            if (Character.isLetter(ch)) {
               letterCounts[Character.toUpperCase(ch) - 'A']++;
            }
         }
         rd.close();
         for (char ch = 'A'; ch <= 'Z'; ch++) {
            System.out.printf("%7d %c\n", letterCounts[ch - 'A'], ch);
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.getMessage());
      }
   }

/*
 * Asks the user for the name of a file and then returns a BufferedReader
 * for that file.  If the file cannot be opened, the method gives the user
 * another chance.  The sysin argument is a Scanner open on the System.in
 * stream.  The prompt gives the user more information about the file.
 */

   private BufferedReader openFileReader(Scanner sysin, String prompt) {
      BufferedReader rd = null;
      while (rd == null) {
         try {
            System.out.print(prompt);
            String name = sysin.nextLine();
            rd = new BufferedReader(new FileReader(name));
         } catch (IOException ex) {
            System.out.println("Can't open that file.");
         }
      }
      return rd;
   }

/* Main program */

   public static void main(String[] args) {
      new LetterFrequency().run();
   }

}
