/*
 * File: SecondHalf.java
 * ---------------------
 * This program tests the secondHalf method from the text.
 */

package edu.stanford.cs.javacs2.ch3;

public class SecondHalf {

   public void run() {
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      for (int i = 0; i <= alphabet.length(); i++) {
         String str = alphabet.substring(0, i);
         System.out.println("secondHalf(\"" + str + "\") -> " +
                            secondHalf(str));
      }
   }

/*
 * Returns the second half of the parameter str, which is defined to
 * include the middle character if the length of str is odd.
 */

   private String secondHalf(String str) {
      return str.substring(str.length() / 2);
   }

/* Main program */

   public static void main(String[] args) {
      new SecondHalf().run();
   }

}
