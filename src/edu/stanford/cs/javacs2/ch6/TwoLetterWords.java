/*
 * File: TwoLetterWords.java
 * -------------------------
 * This program generates a list of the two-letter words by creating every
 * possible two-letter combination and checking whether it is a legal word.
 */

package edu.stanford.cs.javacs2.ch6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class TwoLetterWords {

   public void run() {
      Set<String> english = readWordList("EnglishWords.txt");
      for (char c1 = 'a'; c1 <= 'z'; c1++) {
         for (char c2 = 'a'; c2 <= 'z'; c2++) {
            String word = "" + c1 + c2;
            if (english.contains(word)) System.out.println(word);
         }
      }
   }

/*
 * Reads in a lexicon set from the specified file.
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
      new TwoLetterWords().run();
   }

}
