/*
 * File: Average3.java
 * -------------------
 * This program averages three integers.
 */

package edu.stanford.cs.javacs2.ch1;

import java.util.Scanner;

public class Average3 {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program averages three integers.");
      System.out.print("1st integer: ");
      int n1 = sysin.nextInt();
      System.out.print("2nd integer: ");
      int n2 = sysin.nextInt();
      System.out.print("3rd integer: ");
      int n3 = sysin.nextInt();
      double average = (n1 + n2 + n3) / 3.0;
      System.out.println("The average is " + average);
   }

/* Main program */

   public static void main(String[] args) {
      new Average3().run();
   }

}
