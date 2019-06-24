/*
 * File: CharSetTest.java
 * ----------------------
 * This program tests the CharSet class by creating various letter sets
 * and displaying the results of simple operations.
 */

package edu.stanford.cs.javacs2.ch16;

public class CharSetTest {

   public void run() {
      CharSet letters = new CharSet("abcdefghijklmnopqrstuvwxyz");
      CharSet vowels = new CharSet("aeiou");
      CharSet consonants = letters.subtract(vowels);
      CharSet descenders = new CharSet("gjpqy");
      CharSet highPoints = new CharSet("jkqxz");
      System.out.println("vowels = " + vowels);
      System.out.println("consonants = " + consonants);
      System.out.println("descenders = " + descenders);
      System.out.println("highPoints = " + highPoints);
      System.out.println("descenders U highPoints = " +
                          descenders.union(highPoints));
      System.out.println("descenders ^ highPoints = " +
                          descenders.intersect(highPoints));
   }

/* Main program */

   public static void main(String[] args) {
      new CharSetTest().run();
   }

}
