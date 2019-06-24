/*
 * File: CollegeGraduationRates.java
 * ---------------------------------
 * This program produces a formatted table of college graduation rates
 * by state.  It uses a Scanner to read the data from a data file and
 * then prints it on the console.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CollegeGraduationRates {

   public void run() {
      try {
         BufferedReader rd = new BufferedReader(new FileReader(DATA_FILE));
         System.out.println("      State        Graduates   Rate");
         System.out.println("----------------- ----------- ------");
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");
            String state = scanner.next();
            int grads = scanner.nextInt();
            double rate = scanner.nextDouble();
            System.out.printf("%-16s%,12d%7.1f%%%n", state, grads, rate);
         }
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Constants */

   private static final String DATA_FILE = "CollegeGraduationRates.csv";

/* Main program */

   public static void main(String[] args) {
      new CollegeGraduationRates().run();
   }

}
