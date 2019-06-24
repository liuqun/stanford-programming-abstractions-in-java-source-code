/*
 * File: SevenLetterWords.java
 * ---------------------------
 * This program generates a list of the seven-letter words by iterating
 * through all English words and checking whether the length is seven.
 */

package edu.stanford.cs.javacs2.ch6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class SevenLetterWords {

   public void run() {
      Set<String> english = readWordList("EnglishWords.txt");
      for (String word : english) {
         if (word.length() == 7) System.out.println(word);
      }
   }

/*
 * Reads in a list of words from the specified file and stores them
 * in a set of strings.
 */

   private Set<String> readWordList(String filename) {
      try {
         Set<String> lexicon = new TreeSet<String>();
         BufferedReader rd = new BufferedReader(new FileReader(filename));
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            lexicon.add(line);
         }
         rd.close();
         return lexicon;
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Main program */

   public static void main(String[] args) {
      new SevenLetterWords().run();
   }

}
