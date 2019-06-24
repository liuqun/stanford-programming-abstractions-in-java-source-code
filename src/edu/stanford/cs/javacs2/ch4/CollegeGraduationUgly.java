/*
 * File: CollegeGraduationUgly.java
 * --------------------------------
 * This program produces an ugly, unformatted table of college graduation
 * rates by state.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CollegeGraduationUgly {

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
            System.out.println(state + " " + grads + " " + rate);
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
      new CollegeGraduationUgly().run();
   }

}
