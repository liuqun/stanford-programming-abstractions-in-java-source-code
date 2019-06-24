/*
 * File: GDimension.java
 * ---------------------
 * The GPoint class encapsulates a width and a height to represent a size.
 */

package edu.stanford.cs.graphics;

import java.awt.Dimension;

/**
 * This class is a double-precision version of the <code>Dimension</code>
 * class in <code>java.awt</code>.
 */

public class GDimension {

/**
 * Constructs a new <code>GDimension</code> object with zero values for
 * width and height.
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
 * Constructs a new <code>GDimension</code> object to match an existing one.
 *
 * @param size An existing <code>GDimension</code> object specifying the size
 */

   public GDimension(GDimension size) {
      this(size.width, size.height);
   }

/**
 * Constructs a new <code>GDimension</code> object from an AWT
 * <code>Dimension</code>.
 *
 * @param size An AWT <code>Dimension</code> object specifying the size
 */

   public GDimension(Dimension size) {
      this(size.width, size.height);
   }

/**
 * Returns the width of this <code>GDimension</code>.
 *
 * @return The width of this <code>GDimension</code>
 */

   public double getWidth() {
      return width;
   }

/**
 * Returns the height of this <code>GDimension</code>.
 *
 * @return The height of this <code>GDimension</code>
 */

   public double getHeight() {
      return height;
   }

/**
 * Sets the components of the dimension object from the specified parameters.
 *
 * @param width The new width of the dimension object
 * @param height The new height of the dimension object
 */

   public void setSize(double width, double height) {
      this.width = width;
      this.height = height;
   }

/**
 * Sets the width and height of one <code>GDimension</code> object equal
 * to that of another.
 *
 * @param size A <code>GDimension</code> object specifying the new size
 */

   public void setSize(GDimension size) {
      setSize(size.width, size.height);
   }

/**
 * Returns a new <code>GDimension</code> object equal to this one.
 *
 * @return A new <code>GDimension</code> object with the same size
 */

   public GDimension getSize() {
      return new GDimension(width, height);
   }

/**
 * Converts this <code>GDimension</code> to the nearest integer-based
 * <code>Dimension</code>.
 *
 * @return The closest integer-based <code>Dimension</code> object
 */

   public Dimension toDimension() {
      return new Dimension(GMath.round(width), GMath.round(height));
   }

/**
 * Converts this <code>GDimension</code> to its string representation.
 *
 * @return A string representation of this dimension object
 */

   @Override
   public String toString() {
      return "GDimension(" + GObject.dts(width) + ", " +
                             GObject.dts(height) + ")";
   }

/**
 * Returns an integer hash code for the dimension object.  The hash code for
 * a <code>GDimension</code> is constructed from the hash codes from the
 * <code>float</code> values of the width and height, which are the ones
 * used in the <code>equals</code> method.
 *
 * @return The hash code for this dimension object
 */

   @Override
   public int hashCode() {
      return new Float((float) width).hashCode() ^
                       (37 * new Float((float) height).hashCode());
   }

/**
 * Tests whether two <code>GDimension</code> objects are equal.
 * Because floating-point values are inexact, this method checks for
 * equality by comparing the <code>float</code> values (rather than the
 * <code>double</code> values) of the coordinates.
 *
 * @param obj Any object
 * @return <code>true</code> if the <code>obj</code> is a
 *         <code>GDimension</code> equal to this one, and
 *         <code>false</code> otherwise
 */

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof GDimension)) return false;
      GDimension dim = (GDimension) obj;
      return ((float) width == (float) dim.width)
          && ((float) height == (float) dim.height);
   }

/* Private instance variables */

   private double width;
   private double height;

}
