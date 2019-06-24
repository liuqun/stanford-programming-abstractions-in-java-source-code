/*
 * File: AddTwoIntegers.java
 * -------------------------
 * This program adds two integers and prints their sum.
 */

package edu.stanford.cs.javacs2.ch4;

import java.util.Scanner;

public class AddTwoIntegers {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program adds two integers.");
      System.out.print("1st integer: ");
      int n1 = sysin.nextInt();
      System.out.print("2nd integer: ");
      int n2 = sysin.nextInt();
      int sum = n1 + n2;
      System.out.printf("%d + %d = %d%n", n1, n2, sum);
   }

/* Main program */

   public static void main(String[] args) {
      new AddTwoIntegers().run();
   }

}
