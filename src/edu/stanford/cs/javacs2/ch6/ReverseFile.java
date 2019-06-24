/*
 * File: ReverseFile.java
 * ----------------------
 * This program displays the lines of an input file in reverse order.
 */

package edu.stanford.cs.javacs2.ch6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReverseFile {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      try {
         BufferedReader rd = openFileReader(sysin, "Input file: ");
         ArrayList<String> lines = readEntireFile(rd);
         rd.close();
         for (int i = lines.size() - 1; i >= 0; i--) {
            System.out.println(lines.get(i));
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Reads the entire contents of a file from a reader into an ArrayList */

   private ArrayList<String> readEntireFile(BufferedReader rd) {
      try {
         ArrayList<String> lines = new ArrayList<String>();
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            lines.add(line);
         }
         return lines;
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Asks the user for the name of a file and then returns a BufferedReader
 * for that file.  If the file cannot be opened, the method gives the user
 * another chance.  The sysin argument is a Scanner open on the System.in
 * stream.  The prompt gives the user more information about the file.
 */

   private BufferedReader openFileReader(Scanner sysin, String prompt) {
      BufferedReader rd = null;
      while (rd == null) {
         try {
            System.out.print(prompt);
            String name = sysin.nextLine();
            rd = new BufferedReader(new FileReader(name));
         } catch (IOException ex) {
            System.out.println("Can't open that file.");
         }
      }
      return rd;
   }

/* Main program */

   public static void main(String[] args) {
      new ReverseFile().run();
   }

}
