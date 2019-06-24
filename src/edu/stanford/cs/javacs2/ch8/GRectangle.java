/*
 * File: GRectangle.java
 * ---------------------
 * This file exports a double-precision version of the Rectangle class.
 */

package edu.stanford.cs.javacs2.ch8;

/**
 * This class is a double-precision version of the Rectangle class in java.awt.
 */

public class GRectangle {

/**
 * Constructs a new empty GRectangle.
 */

   public GRectangle() {
      this(0, 0, 0, 0);
   }

/**
 * Constructs a new GRectangle with the specified coordinates and size.
 *
 * @param x The x-coordinate of the rectangle
 * @param y The y-coordinate of the rectangle
 * @param width The width of the rectangle
 * @param height The height of the rectangle
 */

   public GRectangle(double x, double y, double width, double height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

/**
 * Returns the x coordinate of this GRectangle.
 *
 * @return The x coordinate of this GRectangle
 */

   public double getX() {
      return x;
   }

/**
 * Returns the y coordinate of this GRectangle.
 *
 * @return The y coordinate of this GRectangle
 */

   public double getY() {
      return y;
   }

/**
 * Returns the width of this GRectangle.
 *
 * @return The width of this GRectangle
 */

   public double getWidth() {
      return width;
   }

/**
 * Returns the height of this GRectangle.
 *
 * @return The height of this GRectangle
 */

   public double getHeight() {
      return height;
   }

/**
 * Converts this GRectangle to its string representation.
 *
 * @return A string representation of this rectangle
 */

   @Override
   public String toString() {
      return "[" + x + ", " + y + ", " + width + "x" + height + "]";
   }

/* Private instance variables */

   private double x;
   private double y;
   private double width;
   private double height;

}
