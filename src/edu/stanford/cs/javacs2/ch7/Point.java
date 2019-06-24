/*
 * File: Point.java
 * ----------------
 * This file exports a simplified version of the java.awt.Point class.
 */

package edu.stanford.cs.javacs2.ch7;

/**
 * This class combines a pair of <i>x</i> and <i>y</i> coordinates.
 */

public class Point {

/**
 * The <i>x</i> coordinate of this point.
 */

   public int x;

/**
 * The <i>y</i> coordinate of this point.
 */

   public int y;

/**
 * Constructs a new point at the origin (0, 0).
 */

   public Point() {
      x = y = 0;
   }

/**
 * Constructs a new point with the specified coordinates.
 *
 * @param x The <i>x</i> coordinate of the point
 * @param y The <i>y</i> coordinate of the point
 */

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }

/**
 * Converts this point to its string representation.
 *
 * @return A string representation of this point
 */

   @Override
   public String toString() {
      return "(" + x + ", " + y + ")";
   }

}
