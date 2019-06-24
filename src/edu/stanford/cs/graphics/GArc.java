/*
 * File: GArc.java
 * ---------------
 * This file exports a GObject subclass used to display elliptical arcs.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

/**
 * The <code>GArc</code> class is a graphical object whose appearance
 * consists of an arc.  If unfilled, the arc is simply a portion of
 * the circumference of an ellipse; if filled, the arc is a pie-shaped
 * wedge connected to the center of the figure.
 */

public class GArc extends GObject implements GFillable {

/**
 * This constant defines how close (measured in pixel units) a point has
 * to be to an arc before that point is considered to be "contained" within
 * the arc.
 */

   public static final double ARC_TOLERANCE = 2.5;
   // BUG?: THIS SEEMS TO REQUIRE HITTING THE ARC EXACTLY EVEN WITH 2.5

/**
 * Creates a new <code>GArc</code> object consisting of an elliptical arc
 * located at the point (0,&nbsp;0).  For complete descriptions of the
 * other parameters, see the entry for the
 * <a href="#GArc(double, double, double, double, double, double)"
 * ><code>GArc</code></a> constructor that includes explicit
 * <code>x</code> and <code>y</code> parameters.
 *
 * @param width The width of the rectangle in which the arc is inscribed
 * @param height The height of the rectangle in which the arc is inscribed
 * @param start The angle at which the arc begins measured in degrees
 *              counterclockwise from the +x axis
 * @param sweep The extent of the arc, measured in degrees counterclockwise
 */

   public GArc(double width, double height, double start, double sweep) {
      this(0, 0, width, height, start, sweep);
   }

/**
 * Creates a new <code>GArc</code> object consisting of an elliptical
 * arc inscribed in the rectangle with the specified bounds.
 * The <code>start</code> parameter indicates the angle at which the arc
 * begins and is measured in degrees counterclockwise from the +<i>x</i>
 * axis.  Thus, a <code>start</code> angle of 0 indicates an arc
 * that begins along the line running eastward from the center (the 3:00
 * o&rsquo;clock position), a <code>start</code> angle of 135
 * begins along the line running northwest, and a <code>start</code>
 * angle of -90 begins along the line running south (the 6:00
 * o&rsquo;clock position).  The <code>sweep</code> parameter indicates
 * the extent of the arc and is also measured in degrees counterclockwise.
 * A <code>sweep</code> angle of 90 defines a quarter circle extending
 * counterclockwise from the <code>start</code> angle, and a
 * <code>sweep</code> angle of -180 defines a semicircle extending
 * clockwise.
 *
 * @param x The x-coordinate for the rectangle in which the arc is inscribed
 * @param y The y-coordinate for the rectangle in which the arc is inscribed
 * @param width The width of the rectangle in which the arc is inscribed
 * @param height The height of the rectangle in which the arc is inscribed
 * @param start The angle at which the arc begins measured in degrees
 *              counterclockwise from the +x axis
 * @param sweep The extent of the arc, measured in degrees counterclockwise
 */

   public GArc(double x, double y, double width, double height,
                                   double start, double sweep) {
      this.width = width;
      this.height = height;
      this.start = start;
      this.sweep = sweep;
      setLocation(x, y);
   }

/**
 * Sets the starting angle for this <code>GArc</code> object.
 *
 * @param start The new starting angle
 */

   public void setStartAngle(double start) {
      this.start = start;
      repaint();
   }

/**
 * Returns the starting angle for this <code>GArc</code> object.
 *
 * @return The starting angle for this arc
 */

   public double getStartAngle() {
      return start;
   }

/**
 * Sets the sweep angle for this <code>GArc</code> object.
 *
 * @param sweep The new sweep angle
 */

   public void setSweepAngle(double sweep) {
      this.sweep = sweep;
      repaint();
   }

/**
 * Returns the sweep angle for this <code>GArc</code> object.
 *
 * @return The sweep angle for this arc
 */

   public double getSweepAngle() {
      return sweep;
   }

/**
 * Returns the point at which the arc starts.
 *
 * @return The point at which the arc starts
 */

   public GPoint getStartPoint() {
      GPoint pt = getCTM().transform(getArcPoint(start));
      pt.translate(getX(), getY());
      return pt;
   }

/**
 * Returns the point at which the arc ends.
 *
 * @return The point at which the arc ends
 */

   public GPoint getEndPoint() {
      GPoint pt = getCTM().transform(getArcPoint(start + sweep));
      pt.translate(getX(), getY());
      return pt;
   }

/**
 * Changes the arc bounds to the specified values.
 *
 * @param x The x-coordinate for the rectangle in which the arc is inscribed
 * @param y The y-coordinate for the rectangle in which the arc is inscribed
 * @param width The width of the rectangle in which the arc is inscribed
 * @param height The height of the rectangle in which the arc is inscribed
 */

   public void setFrameRectangle(double x, double y,
                                 double width, double height) {
      this.width = width;
      this.height = height;
      setLocation(x, y);
   }

/**
 * Changes the arc bounds to the values from the specified
 * <code>GRectangle</code>.
 *
 * @param bounds A <code>GRectangle</code> specifying the new arc bounds
 */

   public final void setFrameRectangle(GRectangle bounds) {
      setFrameRectangle(bounds.getX(), bounds.getY(),
                        bounds.getWidth(), bounds.getHeight());
   }

/**
 * Returns the bounds of the <code>GRectangle</code> in which this arc is
 * inscribed.  Note that this is usually different from the bounding box
 * returned by <a href="#getBounds()"><code>getBounds</code></a>, which
 * returns the bounding box in which the displayed portion of the arc
 * is contained.
 *
 * @return The <code>GRectangle</code> in which this arc is inscribed
 */

   public GRectangle getFrameRectangle() {
      return new GRectangle(getX(), getY(), width, height);
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
 * Returns a string indicating the parameters of this object.
 */

   @Override
   public String paramString() {
      String tail = super.paramString();
      tail = tail.substring(tail.indexOf(')') + 1);
      GRectangle r = getFrameRectangle();
      String param = "frame=(" + r.getX() + ", " + r.getY() + ", "
                     + r.getWidth() + ", " + r.getHeight() + ")";
      param += ", start=" + start + ", sweep=" + sweep;
      return param + tail;
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      GRectangle bb = new GRectangle(ctm.transform(getArcPoint(start)));
      bb.add(ctm.transform(getArcPoint(start + sweep)));
      double rx = width / 2;
      double ry = height / 2;
      double a = ctm.getScaleX();
      double b = ctm.getShearY();
      double c = ctm.getShearX();
      double d = ctm.getScaleY();
      double tx = Math.atan2(c * height, a * width);
      double ty = Math.atan2(d * height, b * width);
      for (int i = 0; i < 4; i++) {
         double t1 = tx + i * Math.PI / 2;
         double t2 = ty + i * Math.PI / 2;
         if (containsAngle(GMath.toDegrees(t1))) {
            bb.add(ctm.transform(rx + rx * Math.cos(t1),
                                 ry - ry * Math.sin(t1)));
         }
         if (containsAngle(GMath.toDegrees(t2))) {
            bb.add(ctm.transform(rx + rx * Math.cos(t2),
                                 ry - ry * Math.sin(t2)));
         }
      }
      if (isFilled()) bb.add(ctm.transform(rx, ry));
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.  For the
 * <code>GArc</code> class, containment depends on whether the arc
 * is filled.  Filled arcs are a wedge in which containment can be
 * defined in a natural way; unfilled arcs are essentially lines,
 * which means that containment is defined to mean that the point
 * is within <a href="#ARC_TOLERANCE"><code>ARC_TOLERANCE</code></a> pixels
 * of the arc.
 */

   @Override
   protected boolean localContains(double x, double y) {
      double rx = width / 2;
      double ry = height / 2;
      if (rx == 0 || ry == 0) return false;
      double dx = x - rx;
      double dy = y - ry;
      double r = (dx * dx) / (rx * rx) + (dy * dy) / (ry * ry);
      if (isFilled()) {
         if (r > 1.0) return false;
      } else {
         double t = ARC_TOLERANCE / ((rx + ry) / 2);
         if (Math.abs(1.0 - r) > t) return false;
      }
      return containsAngle(GMath.toDegrees(Math.atan2(-dy, dx)));
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      Arc2D arc = new Arc2D.Double(0, 0, width, height,
                                   start, sweep,
                                   isFilled() ? Arc2D.PIE : Arc2D.OPEN);
      if (isFilled()) {
         g.setColor(getFillColor());
         g.fill(arc);
         g.setColor(getColor());
      }
      g.draw(arc);
   }

/* Private methods */

/**
 * Returns the point on the origin-based, untransformed ellipse for the
 * arc at the specified angle.
 */

   private GPoint getArcPoint(double angle) {
      double rx = width / 2;
      double ry = height / 2;
      return new GPoint(rx + rx * GMath.cosDegrees(angle),
                        ry - ry * GMath.sinDegrees(angle));
   }

/**
 * Returns <code>true</code> if the arc contains the specified angle.
 */

   private boolean containsAngle(double theta) {
      double start = Math.min(getStartAngle(),
                              getStartAngle() + getSweepAngle());
      double sweep = Math.abs(getSweepAngle());
      if (sweep >= 360) return true;
      theta = (theta < 0) ? 360 - (-theta % 360) : theta % 360;
      start = (start < 0) ? 360 - (-start % 360) : start % 360;
      if (start + sweep > 360) {
         return theta >= start || theta <= start + sweep - 360;
      } else {
         return theta >= start && theta <= start + sweep;
      }
   }

/* Private instance variables */

   private double width;
   private double height;
   private double start;
   private double sweep;
   private Color fillColor;
   private boolean isFilled;

}
