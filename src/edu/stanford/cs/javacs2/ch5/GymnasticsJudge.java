/*
 * File: GymnasticsJudge.java
 * --------------------------
 * This file reads in an array of scores and computes the average.
 */

package edu.stanford.cs.javacs2.ch5;

import java.util.Scanner;

public class GymnasticsJudge {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.print("Enter number of judges: ");
      int nJudges = sysin.nextInt();
      double[] scores = new double[nJudges];
      for (int i = 0; i < nJudges; i++) {
         System.out.print("Enter score for judge " + (i + 1) + ": ");
         scores[i] = sysin.nextDouble();
      }
      System.out.printf("The average score is %4.2f%n", average(scores));
   }

/*
 * Computes the average of an array of doubles.
 */

   private double average(double[] array) {
      double total = 0;
      for (int i = 0; i < array.length; i++) {
         total += array[i];
      }
      return total / array.length;
   }

/* Main program */

   public static void main(String[] args) {
      new GymnasticsJudge().run();
   }

}
