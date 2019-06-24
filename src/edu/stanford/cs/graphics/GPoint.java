/*
 * File: GPoint.java
 * -----------------
 * The GPoint class encapsulates an x and a y value to represent a point.
 */

package edu.stanford.cs.graphics;

import java.awt.Point;

/**
 * This class is a double-precision version of the <code>Point</code> class
 * in <code>java.awt</code>.
 */

public class GPoint {

/**
 * Constructs a new <code>GPoint</code> at the origin (0, 0).
 */

   public GPoint() {
      this(0, 0);
   }

/**
 * Constructs a new <code>GPoint</code> with the specified coordinates.
 *
 * @param x The x-coordinate of the point
 * @param y The y-coordinate of the point
 */

   public GPoint(double x, double y) {
      this.x = x;
      this.y = y;
   }

/**
 * Constructs a new <code>GPoint</code> from an existing one.
 *
 * @param p The original point
 */

   public GPoint(GPoint p) {
      this(p.x, p.y);
   }

/**
 * Constructs a new <code>GPoint</code> from an existing AWT
 * <code>Point</code>.
 *
 * @param p An AWT <code>Point</code>
 */

   public GPoint(Point p) {
      this(p.x, p.y);
   }

/**
 * Returns the x coordinate of this <code>GPoint</code>.
 *
 * @return The x coordinate of this <code>GPoint</code>
 */

   public double getX() {
      return x;
   }

/**
 * Returns the y coordinate of this <code>GPoint</code>.
 *
 * @return The y coordinate of this <code>GPoint</code>
 */

   public double getY() {
      return y;
   }

/**
 * Sets the location of the <code>GPoint</code> to the specified
 * <code>x</code> and <code>y</code> values.
 *
 * @param x The new x-coordinate for the point
 * @param y The new y-coordinate for the point
 */

   public void setLocation(double x, double y) {
      this.x = x;
      this.y = y;
   }

/**
 * Sets the location of the <code>GPoint</code> to that of an existing one.
 *
 * @param p An existing <code>GPoint</code> specifying the new location
 */

   public void setLocation(GPoint p) {
      setLocation(p.x, p.y);
   }

/**
 * Returns a new <code>GPoint</code> whose coordinates are the same as
 * this one.
 *
 * @return A new point with the same coordinates
 */

   public GPoint getLocation() {
      return new GPoint(x, y);
   }

/**
 * Adjusts the coordinates of a point by the specified <code>dx</code> and
 * <code>dy</code> offsets.
 *
 * @param dx The change in the x direction (positive is rightward)
 * @param dy The change in the y direction (positive is downward)
 */

   public void translate(double dx, double dy) {
      x += dx;
      y += dy;
   }

/**
 * Converts this <code>GPoint</code> to the nearest integer-based
 * <code>Point</code>.
 *
 * @return The closest integer-based <code>Point</code>
 */

   public Point toPoint() {
      return new Point(GMath.round(x), GMath.round(y));
   }

/**
 * Converts this <code>GPoint</code> to its string representation.
 *
 * @return A string representation of this point
 */

   @Override
   public String toString() {
      return "GPoint(" + GObject.dts(x) + ", " + GObject.dts(y) + ")";
   }

/**
 * Returns an integer hash code for the point.  The hash code for a
 * <code>GPoint</code> is constructed from the hash codes from the
 * <code>float</code> values of the coordinates, which are the ones used
 * in the <code>equals</code> method.
 *
 * @return The hash code for this pt
 */

   @Override
   public int hashCode() {
      return new Float((float) x).hashCode() ^
                 (37 * new Float((float) y).hashCode());
   }

/**
 * Tests whether two <code>GPoint</code> objects are equal.
 * Because floating-point values are inexact, this method checks for
 * equality by comparing the <code>float</code> values (rather than the
 * <code>double</code> values) of the coordinates.
 *
 * @param obj Any object
 * @return <code>true</code> if the <code>obj</code> is a <code>GPoint</code>
 *         equal to this one, and <code>false</code> otherwise
 */

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof GPoint)) return false;
      GPoint pt = (GPoint) obj;
      return ((float) x == (float) pt.x) && ((float) y == (float) pt.y);
   }

/* Private instance variables */

   private double x;
   private double y;

}
