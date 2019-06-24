/*
 * File: GRectangle.java
 * ---------------------
 * The GRectangle class encapsulates x, y, width, and height values.
 */

package edu.stanford.cs.graphics;

import java.awt.Rectangle;

/**
 * This class is a double-precision version of the <code>Rectangle</code>
 * class in <code>java.awt</code>.
 */

public class GRectangle {

/**
 * Constructs a new empty <code>GRectangle</code>.
 */

   public GRectangle() {
      this(0, 0, 0, 0);
   }

/**
 * Constructs a new <code>GRectangle</code> with the specified coordinates
 * and size.
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
 * Constructs a <code>GRectangle</code> at the origin with the specified
 * width and height.
 *
 * @param width The width of the rectangle
 * @param height The height of the rectangle
 */

   public GRectangle(double width, double height) {
      this(0, 0, width, height);
   }

/**
 * Constructs a new <code>GRectangle</code> with the specified location
 * and size.
 *
 * @param pt The location of the upper left corner of the rectangle
 * @param size The dimensions of the rectangle
 */

   public GRectangle(GPoint pt, GDimension size) {
      this(pt.getX(), pt.getY(), size.getWidth(), size.getHeight());
   }

/**
 * Constructs a new empty <code>GRectangle</code> at the specified location.
 *
 * @param pt The location of the upper left corner of the rectangle
 */

   public GRectangle(GPoint pt) {
      this(pt.getX(), pt.getY(), 0, 0);
   }

/**
 * Constructs a new <code>GRectangle</code> at the origin with the
 * specified size.
 *
 * @param size The dimensions of the rectangle
 */

   public GRectangle(GDimension size) {
      this(0, 0, size.getWidth(), size.getHeight());
   }

/**
 * Constructs a new <code>GRectangle</code> from an existing one.
 *
 * @param r The original rectangle
 */

   public GRectangle(GRectangle r) {
      this(r.x, r.y, r.width, r.height);
   }

/**
 * Returns the x coordinate of this <code>GRectangle</code>.
 *
 * @return The x coordinate of this <code>GRectangle</code>
 */

   public double getX() {
      return x;
   }

/**
 * Returns the y coordinate of this <code>GRectangle</code>.
 *
 * @return The y coordinate of this <code>GRectangle</code>
 */

   public double getY() {
      return y;
   }

/**
 * Returns the width of this <code>GRectangle</code>.
 *
 * @return The width of this <code>GRectangle</code>
 */

   public double getWidth() {
      return width;
   }

/**
 * Returns the height of this <code>GRectangle</code>.
 *
 * @return The height of this <code>GRectangle</code>
 */

   public double getHeight() {
      return height;
   }

/**
 * Sets the components of a <code>GRectangle</code> from the specified values.
 *
 * @param x The x-coordinate of the rectangle
 * @param y The y-coordinate of the rectangle
 * @param width The width of the rectangle
 * @param height The height of the rectangle
 */

   public void setBounds(double x, double y, double width, double height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

/**
 * Sets the components of a <code>GRectangle</code> from the specified
 * location and size.
 *
 * @param pt The location of the upper left corner of the rectangle
 * @param size The dimensions of the rectangle
 */

   public final void setBounds(GPoint pt, GDimension size) {
      setBounds(pt.getX(), pt.getY(), size.getWidth(), size.getHeight());
   }

/**
 * Sets the bounds of one <code>GRectangle</code> equal to that of another.
 *
 * @param bounds A <code>GRectangle</code> specifying the new bounds
 */

   public final void setBounds(GRectangle bounds) {
      setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
   }

/**
 * Returns a new <code>GRectangle</code> whose bounds are the same as
 * this one.
 *
 * @return A new rectangle with the same bounds
 */

   public GRectangle getBounds() {
      return new GRectangle(this);
   }

/**
 * Sets the location of the <code>GRectangle</code> to the specified
 * <code>x</code> and <code>y</code> values.
 *
 * @param x The new x-coordinate for the rectangle
 * @param y The new y-coordinate for the rectangle
 */

   public void setLocation(double x, double y) {
      this.x = x;
      this.y = y;
   }

/**
 * Sets the location of the <code>GRectangle</code> to the specified point.
 *
 * @param pt The new location for the rectangle
 */

   public void setLocation(GPoint pt) {
      setLocation(pt.getX(), pt.getY());
   }

/**
 * Returns a new <code>GPoint</code> with the location of the rectangle.
 *
 * @return The location of the rectangle as a <code>GPoint</code>
 */

   public GPoint getLocation() {
      return new GPoint(x, y);
   }

/**
 * Adjusts the coordinates of a rectangle by the specified <code>dx</code> and
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
 * Sets the size of the <code>GRectangle</code> to the specified values.
 *
 * @param width The new width of the rectangle
 * @param height The new height of the rectangle
 */

   public void setSize(double width, double height) {
      this.width = width;
      this.height = height;
   }

/**
 * Sets the size of the <code>GRectangle</code> to the specified dimension.
 *
 * @param size The new dimensions of the rectangle
 */

   public void setSize(GDimension size) {
      setSize(size.getWidth(), size.getHeight());
   }

/**
 * Returns a new <code>GDimension</code> object with the size of the
 * <code>GRectangle</code>.
 *
 * @return The size of the rectangle as a <code>GDimension</code>
 */

   public GDimension getSize() {
      return new GDimension(width, height);
   }

/**
 * Adjusts the edges of a rectangle by the specified <code>dx</code> and
 * <code>dy</code> offsets along each of its borders.
 *
 * @param dx The offset to extend each of the left and right borders
 * @param dy The offset to extend each of the top and bottom borders
 */

   public void grow(double dx, double dy) {
      x -= dx;
      y -= dy;
      width += 2 * dx;
      height += 2 * dy;
   }

/**
 * Returns <code>true</code> if the rectangle is empty.
 *
 * @return <code>true</code> if the rectangle is empty, and
 *         <code>false</code> otherwise
 */

   public boolean isEmpty() {
      return width <= 0 || height <= 0;
   }

/**
 * Returns <code>true</code> if the <code>GRectangle</code> contains the
 * specified point.
 *
 * @param x The x-coordinate of the point being tested
 * @param y The y-coordinate of the point being tested
 * @return <code>true</code> if the rectangle contains
 *         (<code>x</code>,&nbsp;<code>y</code>),
 *         and <code>false</code> otherwise
 */

   public boolean contains(double x, double y) {
      return x >= x && y >= y && x < x + width && y < y + height;
   }

/**
 * Returns <code>true</code> if the <code>GRectangle</code> contains the
 * specified point.
 *
 * @param pt The point being tested
 * @return <code>true</code> if the rectangle contains <code>pt</code>,
 *         and <code>false</code> otherwise
 */

   public boolean contains(GPoint pt) {
      return contains(pt.getX(), pt.getY());
   }

/**
 * Returns <code>true</code> if <code>r1</code> and <code>r2</code> have a
 * nonempty intersection.
 *
 * @param r2 A second rectangle
 * @return <code>true</code> if the two rectangles intersect, and
 *         <code>false</code> otherwise
 */

   public boolean intersects(GRectangle r2) {
      GRectangle r1 = this;
      if (r1.x > r2.x + r2.width) return false;
      if (r1.y > r2.y + r2.height) return false;
      if (r2.x > r1.x + r1.width) return false;
      if (r2.y > r1.y + r1.height) return false;
      return true;
   }

/**
 * Returns the largest rectangle that is contained in both
 * <code>r1</code> and <code>r2</code>.
 *
 * @param r2 A second rectangle
 * @return The intersection of this rectangle and <code>r2</code>
 */

   public GRectangle intersection(GRectangle r2) {
      GRectangle r1 = this;
      double x1 = Math.max(r1.x, r2.x);
      double y1 = Math.max(r1.y, r2.y);
      double x2 = Math.min(r1.x + r1.width, r2.x + r2.width);
      double y2 = Math.min(r1.y + r1.height, r2.y + r2.height);
      return new GRectangle(x1, y1, x2 - x1, y2 - y1);
   }

/**
 * Returns the smallest rectangle that contains both
 * <code>r1</code> and <code>r2</code>.
 *
 * @param r2 A second rectangle
 * @return The union of this rectangle and <code>r2</code>
 */

   public GRectangle union(GRectangle r2) {
      if (isEmpty()) return new GRectangle(r2);
      if (r2.isEmpty()) return new GRectangle(this);
      GRectangle r1 = this;
      double x1 = Math.min(r1.x, r2.x);
      double y1 = Math.min(r1.y, r2.y);
      double x2 = Math.max(r1.x + r1.width, r2.x + r2.width);
      double y2 = Math.max(r1.y + r1.height, r2.y + r2.height);
      return new GRectangle(x1, y1, x2 - x1, y2 - y1);
   }

/**
 * Adjusts the bounds of the current <code>GRectangle</code> so that it
 * contains the rectangle represented by the argument.
 *
 * @param r A new rectangle to include in this one
 */

   public void add(GRectangle r) {
      if (r.isEmpty()) return;
      double x2 = Math.max(x + width, r.x + r.width);
      double y2 = Math.max(y + height, r.y + r.height);
      x = Math.min(r.x, x);
      y = Math.min(r.y, y);
      width = x2 - x;
      height = y2 - y;
   }

/**
 * Adds the specified point to the rectangle.
 *
 * @param x The x coordinate of the new point
 * @param y The y coordinate of the new point
 */

   public void add(double x, double y) {
      if (x < this.x) {
         width += this.x - x;
         this.x = x;
      } else if (x > this.x + width) {
         width = x - this.x;
      }
      if (y < this.y) {
         height += this.y - y;
         this.y = y;
      } else if (y > this.y + height) {
         height = y - this.y;
      }
   }

/**
 * Adds the specified point to the rectangle.
 *
 * @param pt The point to be added
 */

   public void add(GPoint pt) {
      add(pt.getX(), pt.getY());
   }

/**
 * Converts this <code>GRectangle</code> to the nearest integer-based
 * <code>Rectangle</code>.
 *
 * @return The closest integer-based <code>Rectangle</code>
 */

   public Rectangle toRectangle() {
      return new Rectangle(GMath.round(x), GMath.round(y),
                           GMath.round(width), GMath.round(height));
   }

/**
 * Returns an integer hash code for the rectangle.  The hash code for a
 * <code>GRectangle</code> is constructed from the hash codes from the
 * <code>float</code> values of the coordinates, which are the ones used
 * in the <code>equals</code> method.
 *
 * @return The hash code for this rectangle
 */

   @Override
   public int hashCode() {
      int hash = new Float((float) x).hashCode();
      hash = (37 * hash) ^ new Float((float) y).hashCode();
      hash = (37 * hash) ^ new Float((float) width).hashCode();
      hash = (37 * hash) ^ new Float((float) height).hashCode();
      return hash;
   }

/**
 * Tests whether two <code>GRectangle</code> objects are equal.
 * Because floating-point values are inexact, this method checks for
 * equality by comparing the <code>float</code> values (rather than the
 * <code>double</code> values) of the coordinates.
 *
 * @param obj Any object
 * @return <code>true</code> if the <code>obj</code> is a
 *         <code>GRectangle</code> equal to this one,
 *         and <code>false</code> otherwise
 */

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof GRectangle)) return false;
      GRectangle r = (GRectangle) obj;
      if ((float) x != (float) r.x) return false;
      if ((float) y != (float) r.y) return false;
      if ((float) width != (float) r.width) return false;
      if ((float) height != (float) r.height) return false;
      return true;
   }

/**
 * Converts this <code>GRectangle</code> to its string representation.
 *
 * @return A string representation of this rectangle
 */

   @Override
   public String toString() {
      return "GRectangle(" + GObject.dts(x) + ", " + GObject.dts(y) + ", " +
             GObject.dts(width) + ", " + GObject.dts(height) + ")";
   }

/* Private instance variables */

   private double x;
   private double y;
   private double width;
   private double height;

}
