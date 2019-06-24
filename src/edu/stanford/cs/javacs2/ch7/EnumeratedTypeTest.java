/*
 * File: EnumeratedTypeTest.java
 * -----------------------------
 * This program simply checks the syntax of the enumerated type examples
 * in the chapter.
 */

package edu.stanford.cs.javacs2.ch7;

public class EnumeratedTypeTest {

   public void run() {
      for (Suit s : Suit.values()) {
         System.out.println(s + " -> " + getColor(s));
      }
   }

   private String getColor(Suit s) {
      switch (s) {
       case CLUBS: case SPADES: return "BLACK";
       case DIAMONDS: case HEARTS: return "RED";
      }
      throw new RuntimeException("Illegal suit value");
   }

/* Main program */

   public static void main(String[] args) {
      new EnumeratedTypeTest().run();
   }

}
