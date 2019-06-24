/*
 * File: GPoint.java
 * -----------------
 * This file exports a double-precision version of the Point class.
 */

package edu.stanford.cs.javacs2.ch8;

public class GPoint {

/*
 * Constructs a new GPoint at the origin (0, 0).
 */

   public GPoint() {
      x = y = 0.0;
   }

/*
 * Constructs a new GPoint with the specified coordinates.
 */

   public GPoint(double x, double y) {
      this.x = x;
      this.y = y;
   }

/*
 * Returns the x coordinate of this GPoint.
 */

   public double getX() {
      return x;
   }

/*
 * Returns the y coordinate of this GPoint.
 */

   public double getY() {
      return y;
   }

/*
 * Converts this GPoint to its string representation.
 */

   @Override
   public String toString() {
      return "(" + x + ", " + y + ")";
   }

/* Private instance variables */

   private double x;
   private double y;

}
