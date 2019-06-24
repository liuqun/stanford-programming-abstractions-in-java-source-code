/*
 * File: GDimension.java
 * ---------------------
 * This file exports a double-precision version of the Dimension class.
 */

package edu.stanford.cs.javacs2.ch8;

/**
 * This class is a double-precision version of the Dimension class in
 * the java.awt package.
 */

public class GDimension {

/**
 * Constructs a new dimension object with zero values for width and height.
 */

   public GDimension() {
      this(0, 0);
   }

/**
 * Constructs a new dimension object with the specified components.
 *
 * @param width The width of the dimension object
 * @param height The height of the dimension object
 */

   public GDimension(double width, double height) {
      this.width = width;
      this.height = height;
   }

/**
 * Returns the width of this GDimension.
 *
 * @return The width of this GDimension
 */

   public double getWidth() {
      return width;
   }

/**
 * Returns the height of this GDimension.
 *
 * @return The height of this GDimension
 */

   public double getHeight() {
      return height;
   }

/**
 * Converts this GDimension to its string representation.
 *
 * @return A string representation of this dimension object
 */

   @Override
   public String toString() {
      return "(" + width + "x" + height + ")";
   }

/* Private instance variables */

   private double width;
   private double height;

}
