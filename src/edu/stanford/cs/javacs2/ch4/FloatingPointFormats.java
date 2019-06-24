/*
 * File: FloatingPointFormats.java
 * -------------------------------
 * This program demonstrates various options for floating-point output
 * by displaying three different constants (pi, the speed of light in
 * meters/second, and the fine-structure constant).  These constants
 * are chosen because they illustrate a range of exponent scales.
 */

package edu.stanford.cs.javacs2.ch4;

public class FloatingPointFormats {

   public void run() {
      System.out.printf("Floating-point format (%%f):%n%n");
      showConstants("f");
      System.out.printf("%nExponential format (%%E):%n%n");
      showConstants("E");
      System.out.printf("%nGeneral format (%%G):%n%n");
      showConstants("G");
   }

/*
 * Displays the three constants using the specified format and several
 * different values for the number of digits (d).
 */

   private void showConstants(String format) {
      System.out.println(" d |     pi     | speed of light | fine structure");
      System.out.println("---+------------+----------------+----------------");
      for (int d = 1; d <= 4; d++) {
         System.out.printf("%2d |", d);
         System.out.printf("%11." + d + format + " |", PI);
         System.out.printf("%15." + d + format + " |", SPEED_OF_LIGHT);
         System.out.printf("%14." + d + format + "%n", FINE_STRUCTURE);
      }
   }

/* Constants */

   private static final double PI = 3.14159265358979323846;
   private static final double SPEED_OF_LIGHT = 2.99792458E+8;
   private static final double FINE_STRUCTURE = 7.2573525E-3;

/* Main program */

   public static void main(String[] args) {
      new FloatingPointFormats().run();
   }

}
