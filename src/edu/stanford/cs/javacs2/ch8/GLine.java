/*
 * File: GLine.java
 * ----------------
 * This file exports a GObject subclass that displays a line segment.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Graphics;

public class GLine extends GObject {

/* Constructs a line segment from its endpoints */

   public GLine(double x1, double y1, double x2, double y2) {
      setLocation(x1, y1);
      dx = x2 - x1;
      dy = y2 - y1;
   }

/* Sets the start point without changing the end point */

   public void setStartPoint(double x, double y) {
      dx = x - this.x;
      dy = y - this.y;
      repaint();
   }

/* Sets the end point without changing the start point */

   public void setEndPoint(double x, double y) {
      dx += this.x - x;
      dy += this.y - y;
      setLocation(x, y);
   }

/* Implements the paint operation for this graphical object */

   @Override
   public void paint(Graphics g) {
      g.setColor(color);
      g.drawLine(round(x), round(y), round(x + dx), round(y + dy));
   }

/* Private instance variables */

   private double dx;
   private double dy;

}
