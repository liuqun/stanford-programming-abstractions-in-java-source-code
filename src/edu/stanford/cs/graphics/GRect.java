/*
 * File: GRect.java
 * ----------------
 * This file exports a GObject subclass that displays a rectangle.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * The <code>GRect</code> class is a graphical object whose appearance
 * consists of a rectangular box.
 */

public class GRect extends GObject implements GFillable, GResizable {

/**
 * Constructs a new rectangle with the specified width and height,
 * positioned at the origin.
 *
 * @param width The width of the rectangle in pixels
 * @param height The height of the rectangle in pixels
 */

   public GRect(double width, double height) {
      this(0, 0, width, height);
   }

/**
 * Constructs a new rectangle with the specified bounds.
 *
 * @param x The x-coordinate of the upper left corner
 * @param y The y-coordinate of the upper left corner
 * @param width The width of the rectangle in pixels
 * @param height The height of the rectangle in pixels
 */

   public GRect(double x, double y, double width, double height) {
      this.width = width;
      this.height = height;
      setLocation(x, y);
   }

/**
 * Sets whether this object is filled.
 *
 * @param fill <code>true</code> if the object should be filled,
 *             <code>false</code> for an outline
 */

   @Override
   public void setFilled(boolean fill) {
      isFilled = fill;
      repaint();
   }

/**
 * Returns whether this object is filled.
 *
 * @return The color used to display the object
 */

   @Override
   public boolean isFilled() {
      return isFilled;
   }

/**
 * Sets the color used to display the filled region of this object.
 *
 * @param color The color used to display the filled region of this object
 */

   @Override
   public void setFillColor(Color color) {
      fillColor = color;
      repaint();
   }

/**
 * Returns the color used to display the filled region of this object.  If
 * none has been set, <code>getFillColor</code> returns the color of the
 * object.
 *
 * @return The color used to display the filled region of this object
 */

   @Override
   public Color getFillColor() {
      return (fillColor == null) ? getColor() : fillColor;
   }

/**
 * Changes the size of the untransformed object so that it has the
 * specified width and height.  This method throws a runtime
 * exception if the object has been transformed.
 *
 * @param width The new width of the untransformed object
 * @param height The new height of the untransformed object
 */

   @Override
   public void setSize(double width, double height) {
      if (isTransformed()) {
         throw new RuntimeException("setSize called on transformed object");
      }
      this.width = width;
      this.height = height;
      repaint();
   }

/**
 * Changes the size of this object to match that of the specified
 * <code>GDimension</code> object.  This method throws a runtime
 * exception if the object has been transformed.
 *
 * @param size A <code>GDimension</code> object specifying the new size
 */

   @Override
   public final void setSize(GDimension size) {
      setSize(size.getWidth(), size.getHeight());
   }

/**
 * Changes the bounds of this object to the specified values.  This method
 * throws a runtime exception if the object has been transformed.
 *
 * @param x The new x-coordinate for the untransformed object
 * @param y The new y-coordinate for the untransformed object
 * @param width The new width of the untransformed object
 * @param height The new height of the untransformed object
 */

   @Override
   public void setBounds(double x, double y, double width, double height) {
      if (isTransformed()) {
         throw new RuntimeException("setBounds called on transformed object");
      }
      this.width = width;
      this.height = height;
      setLocation(x, y);
   }

/**
 * Changes the bounds of this object to match the specified
 * <code>GRectangle</code>.  This method throws a runtime exception if
 * the object has been transformed.
 *
 * @param bounds A <code>GRectangle</code> specifying the new bounds
 */

   @Override
   public final void setBounds(GRectangle bounds) {
      setBounds(bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      GRectangle bb = new GRectangle(ctm.transform(0, 0));
      bb.add(ctm.transform(width, 0));
      bb.add(ctm.transform(0, height));
      bb.add(ctm.transform(width, height));
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.
 */

   @Override
   protected boolean localContains(double x, double y) {
      return x >= 0 && x < width && y >= 0 && y < height;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      Rectangle2D r = new Rectangle2D.Double(0, 0, width, height);
      if (isFilled()) {
         g.setColor(getFillColor());
         g.fill(r);
         g.setColor(getColor());
      }
      g.draw(r);
   }

   protected double getFrameWidth() {
      return width;
   }

   protected double getFrameHeight() {
      return height;
   }

/* Private instance variables */

   private double width;
   private double height;
   private boolean isFilled;
   private Color fillColor;

}
