/*
 * File: GOval.java
 * ----------------
 * This file exports a GObject subclass that displays an ellipse.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * The <code>GOval</code> class is a graphical object whose appearance
 * consists of an oval.
 */

public class GOval extends GObject implements GFillable, GResizable {

/**
 * Constructs a new oval with the specified width and height,
 * positioned at the origin.
 *
 * @param width The width of the oval in pixels
 * @param height The height of the oval in pixels
 */

   public GOval(double width, double height) {
      this(0, 0, width, height);
   }

/**
 * Constructs a new oval with the specified bounds.
 *
 * @param x The x-coordinate of the upper left corner
 * @param y The y-coordinate of the upper left corner
 * @param width The width of the oval in pixels
 * @param height The height of the oval in pixels
 */

   public GOval(double x, double y, double width, double height) {
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
   public final void setSize(double width, double height) {
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
   public void setBounds(GRectangle bounds) {
      setBounds(bounds.getX(), bounds.getY(),
                bounds.getWidth(), bounds.getHeight());
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      double rx = width / 2;
      double ry = height / 2;
      double a = ctm.getScaleX();
      double b = ctm.getShearY();
      double c = ctm.getShearX();
      double d = ctm.getScaleY();
      double tx = Math.atan2(c * height, a * width);
      double ty = Math.atan2(d * height, b * width);
      GRectangle bb = new GRectangle(ctm.transform(rx, ry));
      for (int i = 0; i < 4; i++) {
         double t1 = tx + i * Math.PI / 2;
         double t2 = ty + i * Math.PI / 2;
         bb.add(ctm.transform(rx + rx * Math.cos(t1), ry - ry * Math.sin(t1)));
         bb.add(ctm.transform(rx + rx * Math.cos(t2), ry - ry * Math.sin(t2)));
      }
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.
 */

   @Override
   protected boolean localContains(double x, double y) {
      double rx = width / 2;
      double ry = height / 2;
      double tx = x - rx;
      double ty = y - ry;
      return (tx * tx) / (rx * rx) + (ty * ty) / (ry * ry) <= 1.0;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      Ellipse2D oval = new Ellipse2D.Double(0, 0, width, height);
      if (isFilled()) {
         g.setColor(getFillColor());
         g.fill(oval);
         g.setColor(getColor());
      }
      g.draw(oval);
   }

/* Private instance variables */

   private double width;
   private double height;
   private boolean isFilled;
   private Color fillColor;

}
