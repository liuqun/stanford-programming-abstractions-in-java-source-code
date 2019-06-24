/*
 * File: AirportCodes.java
 * -----------------------
 * This program looks up a three-letter airport code in a Map.
 */

package edu.stanford.cs.javacs2.ch6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AirportCodes {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      Map<String,String> airportCodes = readCodeFile("AirportCodes.txt");
      while (true) {
         System.out.print("Airport code: ");
         String code = sysin.nextLine().toUpperCase();
         if (code.equals("")) break;
         if (airportCodes.containsKey(code)) {
            System.out.println(code + " is in " + airportCodes.get(code));
         } else {
            System.out.println("There is no such airport code");
         }
      }
   }

/**
 * Reads a data file representing airport codes and creates a map linking
 * the airport code with the corresponding city names.  Each line in the
 * data file must consist of a three-letter code, an equal sign, and the
 * city name for that airport.  This code, however, does not check that the
 * line is properly formatted, which is left to the reader as an exercise.
 */

   private Map<String,String> readCodeFile(String filename) {
      Map<String,String> map = new TreeMap<String,String>();
      try {
         BufferedReader rd = new BufferedReader(new FileReader(filename));
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            String code = line.substring(0, 3).toUpperCase();
            String city = line.substring(4);
            map.put(code, city);
         }
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.getMessage());
      }
      return map;
   }

/* Main program */

   public static void main(String[] args) {
      new AirportCodes().run();
   }

}
