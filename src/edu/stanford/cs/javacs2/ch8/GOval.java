/*
 * File: GOval.java
 * ----------------
 * This file exports a GObject subclass that displays an oval shape.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import java.awt.Graphics;

public class GOval extends GObject implements GFillable {

/* Constructs a new oval with the specified bounds */

   public GOval(double x, double y, double width, double height) {
      setLocation(x, y);
      setSize(width, height);
   }

/* Changes the width and height of the bounding rectangle */

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
         g.fillOval(round(x), round(y), round(width), round(height));
      }
      g.setColor(color);
      g.drawOval(round(x), round(y), round(width), round(height));
   }

/* Private instance variables */

   private double width, height;
   private boolean isFilled;
   private Color fillColor;

}
