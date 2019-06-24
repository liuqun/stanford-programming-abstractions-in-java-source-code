/*
 * File: AddThreeNumbers.java
 * --------------------------
 * This program adds three floating-point numbers and prints their sum.
 */

package edu.stanford.cs.javacs2.ch1;

import java.util.Scanner;

public class AddThreeNumbers {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.println("This program adds three numbers.");
      System.out.print("1st number: ");
      double n1 = sysin.nextDouble();
      System.out.print("2nd number: ");
      double n2 = sysin.nextDouble();
      System.out.print("3rd number: ");
      double n3 = sysin.nextDouble();
      double sum = n1 + n2 + n3;
      System.out.println("The sum is " + sum);
   }

/* Main program */

   public static void main(String[] args) {
      new AddThreeNumbers().run();
   }

}
