/*
 * File: GObject.java
 * ------------------
 * This file exports the GObject class at the top of the graphics hierarchy.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GObject {

/* Initializes the color of this object to BLACK */

   protected GObject() {
      color = Color.BLACK;
   }

/* Paints the object using the specified graphics context */

   public abstract void paint(Graphics g);

/* Sets the location of this object to the point (x, y) */

   public void setLocation(double x, double y) {
      this.x = x;
      this.y = y;
      repaint();
   }

/* Sets the color used to display this object */

   public void setColor(Color color) {
      this.color = color;
      repaint();
   }

/* Signals that the object needs to be repainted */

   protected void repaint() {
      if (gc != null) gc.repaint();
   }

/* Helper method used by subclasses to round a double to an int */

   protected int round(double x) {
      return (int) Math.round(x);
   }

/* Protected instance variables */

   protected double x;
   protected double y;
   protected Color color;
   protected GCanvas gc;

}
