/*
 * File: AddThreeIntegers.java
 * ---------------------------
 * This program adds three integers and prints their sum.
 */

package edu.stanford.cs.javacs2.ch1;

import java.util.Scanner;

public class AddThreeIntegers {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program adds three integers.");
      System.out.print("1st integer: ");
      int n1 = sysin.nextInt();
      System.out.print("2nd integer: ");
      int n2 = sysin.nextInt();
      System.out.print("3rd integer: ");
      int n3 = sysin.nextInt();
      int sum = n1 + n2 + n3;
      System.out.println("The sum is " + sum);
   }

/* Main program */

   public static void main(String[] args) {
      new AddThreeIntegers().run();
   }

}
