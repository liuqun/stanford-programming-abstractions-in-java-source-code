/*
 * File: SystemConsole.java
 * ------------------------
 * This file implements the Console interface using the standard streams.
 */

package edu.stanford.cs.console;

import java.io.IOException;

/**
 * This class implements <code>Console</code> using <code>System.in</code>
 * and <code>System.out</code>.
 */

public class SystemConsole implements Console {

/*
 * Implementation notes: print, println, and printf
 * ------------------------------------------------
 * These methods simply forward the request to System.out.
 */

   @Override
   public void print(Object value) {
      System.out.print(value);
   }

   @Override
   public void println() {
      System.out.println();
   }

   @Override
   public void println(Object value) {
      System.out.println(value);
   }

   @Override
   public void printf(String format, Object... args) {
      System.out.printf(format, args);
   }

   @Override
   public void format(String format, Object... args) {
      System.out.printf(format, args);
   }

/*
 * Implementation notes: nextLine
 * ------------------------------
 * This method reads characters until it finds an end-of-line sequence.
 * This version includes additional code to recognize all four end-of-line
 * sequences ("\n", "\r", "\r\n", and "\n\r").
 */

   @Override
   public String nextLine() {
      try {
         String line = "";
         while (true) {
            int ch = System.in.read();
            if (ch == -1) return null;
            if (ch == '\r') {
               if (prevChar != '\n') { prevChar = ch; break; }
            } else if (ch == '\n') {
               if (prevChar != '\r') { prevChar = ch; break; }
            } else {
               line += (char) ch;
            }
            prevChar = ch;
         }
         return line;
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

   @Override
   public String nextLine(String prompt) {
      if (prompt != null) print(prompt);
      return nextLine();
   }

/*
 * Implementation notes: nextInt and nextDouble
 * --------------------------------------------
 * These methods use a try statement to catch errors in numeric formatting.
 * If an error occurs, the user is given another chance to enter the data.
 */

   @Override
   public int nextInt() {
      return nextInt(null);
   }

   @Override
   public int nextInt(String prompt) {
      while (true) {
         String line = nextLine(prompt);
         try {
            return Integer.parseInt(line);
         } catch (NumberFormatException ex) {
            println("Illegal integer format");
            if (prompt == null) prompt = "Retry: ";
         }
      }
   }

   @Override
   public double nextDouble() {
      return nextDouble(null);
   }

   @Override
   public double nextDouble(String prompt) {
      while (true) {
         String line = nextLine(prompt);
         try {
            return Double.parseDouble(line);
         } catch (NumberFormatException ex) {
            println("Illegal floating-point format");
            if (prompt == null) prompt = "Retry: ";
         }
      }
   }

/* Private instance variables */

   private int prevChar;

}
