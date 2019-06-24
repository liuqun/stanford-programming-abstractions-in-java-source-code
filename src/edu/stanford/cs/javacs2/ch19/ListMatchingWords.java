/*
 * File: ListMatchingWords.java
 * ----------------------------
 * This program lists all words in the EnglishWords.txt file that match
 * a predicate function specified by the client as a lambda expression.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.javacs2.ch8.Lexicon;

public class ListMatchingWords {

   public void run() {
      Lexicon english = new Lexicon("EnglishWords.txt");
      listMatchingWords(english,
                        new Predicate<String>() {
                           public boolean test(String s) {
                              return s.length() == 10 &&
                                     s.startsWith("th") &&
                                     s.endsWith("y");
                           }
                        });
   }

   private void listMatchingWords(Lexicon lexicon, Predicate <String> fn) {
      for (String word : lexicon) {
         if (fn.test(word)) System.out.println(word);
      }
   }

/* Main program */

   public static void main(String[] args) {
      new ListMatchingWords().run();
   }

}
