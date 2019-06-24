/*
 * File: WordFrequency.java
 * ------------------------
 * This program computes the frequency of words in a text file.
 */

package edu.stanford.cs.javacs2.ch6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordFrequency {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      try {
         BufferedReader rd = openFileReader(sysin, "Input file: ");
         Map<String,Integer> wordCounts = new TreeMap<String,Integer>();
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            countWords(line, wordCounts);
         }
         rd.close();
         displayCounts(wordCounts);
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Breaks a line into words, updating the word counts.
 */

   private void countWords(String line, Map<String,Integer> wordCounts) {
      String word = "";
      for (int i = 0; i < line.length(); i++) {
         char ch = line.charAt(i);
         if (Character.isLetter(ch)) {
            word += Character.toLowerCase(ch);
         } else {
            if (!word.isEmpty()) {
               incrementCount(word, wordCounts);
               word = "";
            }
         }
      }
      if (!word.isEmpty()) incrementCount(word, wordCounts);
   }

/*
 * Increments the count for word in the map.
 */

   private void incrementCount(String word, Map<String,Integer> wordCounts) {
      if (wordCounts.containsKey(word)) {
         wordCounts.put(word, wordCounts.get(word) + 1);
      } else {
         wordCounts.put(word, 1);
      }
   }

/*
 * Displays the word count along with the word.
 */

   private void displayCounts(Map<String,Integer> wordCounts) {
      for (String word : wordCounts.keySet()) {
         System.out.printf("%4d  %s%n", wordCounts.get(word), word);
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
      new WordFrequency().run();
   }

}
