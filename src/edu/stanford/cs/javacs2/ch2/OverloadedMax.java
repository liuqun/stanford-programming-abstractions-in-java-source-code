/*
 * File: OverloadedMax.java
 * ------------------------
 * This file tests the overloaded versions of max.
 */

package edu.stanford.cs.javacs2.ch2;

public class OverloadedMax {

   public void run() {
      System.out.println("max('A', 'Z') = " + max('A', 'Z') +
                         typeLabel(max('A', 'Z')));
      System.out.println("max(3, 2) = " + max(3, 2) +
                         typeLabel(max(3, 2)));
      System.out.println("max(3L, 2L) = " + max(3L, 2L) +
                         typeLabel(max(3L, 2L)));
      System.out.println("max(3.14159, 2.71828) = " + max(3.14159, 2.71828) +
                         typeLabel(max(3.14159, 2.71828)));
      System.out.println("max(-1.0, 5) = " + max(-1.0, 5) +
                         typeLabel(max(-1.0, 5)));
   }

   private String typeLabel(Object obj) {
      String classname = obj.getClass().getName();
      int dot = classname.lastIndexOf('.');
      return " (" + classname.substring(dot + 1) + ")";
   }

/**
 * Returns the larger of the values x and y.  This method is overloaded
 * for each of the primitive numeric types.
 */

   public byte max(byte x, byte y) {
      return (x > y) ? x : y;
   }

   public char max(char x, char y) {
      return (x > y) ? x : y;
   }

   public short max(short x, short y) {
      return (x > y) ? x : y;
   }

   public int max(int x, int y) {
      return (x > y) ? x : y;
   }

   public long max(long x, long y) {
      return (x > y) ? x : y;
   }

   public float max(float x, float y) {
      return (x > y) ? x : y;
   }

   public double max(double x, double y) {
      return (x > y) ? x : y;
   }

/* Main program */

   public static void main(String[] args) {
      new OverloadedMax().run();
   }

}
