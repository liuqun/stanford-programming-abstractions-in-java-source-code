/*
 * File: LexiconSubclassTest.java
 * ------------------------------
 * This program tests the Lexicon abstraction implemented as a subclass
 * of TreeMap.
 */

package edu.stanford.cs.javacs2.ch8;

public class LexiconSubclassTest {

   public void run() {
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

/* Main program */

   public static void main(String[] args) {
      new LexiconSubclassTest().run();
   }

}
