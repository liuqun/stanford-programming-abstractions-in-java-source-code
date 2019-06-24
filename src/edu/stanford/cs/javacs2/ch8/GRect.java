/*
 * File: GRect.java
 * ----------------
 * This class exports a GObject subclass that displays a rectangle.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import java.awt.Graphics;

public class GRect extends GObject implements GFillable {

/* Constructs a new rectangle with the specified bounds */

   public GRect(double x, double y, double width, double height) {
      setLocation(x, y);
      setSize(width, height);
   }

/* Changes the width and height of this rectangle */

   public void setSize(double width, double height) {
      this.width = width;
      this.height = height;
   }

/* Sets whether this object is filled */

   @Override
   public void setFilled(boolean fill) {
      isFilled = fill;
      repaint();
   }

/* Sets the color used to fill this object */

   @Override
   public void setFillColor(Color color) {
      fillColor = color;
      repaint();
   }

/* Implements the paint operation for this graphical object */

   @Override
   public void paint(Graphics g) {
      if (isFilled) {
         g.setColor((fillColor == null) ? color : fillColor);
         g.fillRect(round(x), round(y), round(width), round(height));
      }
      g.setColor(color);
      g.drawRect(round(x), round(y), round(width), round(height));
   }

/* Private instance variables */

   private double width, height;
   private boolean isFilled;
   private Color fillColor;

}
