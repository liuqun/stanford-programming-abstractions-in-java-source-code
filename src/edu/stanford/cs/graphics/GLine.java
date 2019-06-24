/*
 * File: GLine.java
 * ----------------
 * The file exports a GObject subclass that displays a line segment.
 */

package edu.stanford.cs.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * The <code>GLine</code> class is a graphical object whose appearance
 * consists of a line segment.
 */

public class GLine extends GObject {

/**
 * This constant defines how close (measured in pixel units) a point has
 * to be to a line before that point is considered to be "contained" within
 * the line.
 */

   public static final double LINE_TOLERANCE = 1.5;

/**
 * Constructs a line segment from its endpoints.  The point
 * (<code>x0</code>,&nbsp;<code>y0</code>) defines the start of the
 * line and the point (<code>x1</code>,&nbsp;<code>y1</code>) defines
 * the end.
 *
 * @param x0 The x-coordinate of the start of the line
 * @param y0 The y-coordinate of the start of the line
 * @param x1 The x-coordinate of the end of the line
 * @param y1 The y-coordinate of the end of the line
 */

   public GLine(double x0, double y0, double x1, double y1) {
      setLocation(x0, y0);
      dx = x1 - x0;
      dy = y1 - y0;
   }

/**
 * Sets the initial point in the line to (<code>x</code>,&nbsp;<code>y</code>),
 * leaving the end point unchanged.  This method is therefore different from
 * <a href="#setLocation(double, double)"><code>setLocation</code></a>, which
 * moves both components of the line segment.
 *
 * @param x The new x-coordinate of the origin
 * @param y The new y-coordinate of the origin
 */

   public void setStartPoint(double x, double y) {
      GPoint pt = getCTM().transform(dx, dy);
      pt.setLocation(pt.getX() - x, pt.getY() - y);
      pt = getITM().transform(pt);
      dx = pt.getX();
      dy = pt.getY();
      setLocation(x, y);
   }

/**
 * Returns the coordinates of the initial point in the line.  This method is
 * identical to <a href="#getLocation()"><code>getLocation</code></a> and
 * exists only to provide symmetry with
 * <a href="#setStartPoint(double, double)"><code>setStartPoint</code></a>.
 *
 * @return The coordinates of the origin of the line
 */

   public GPoint getStartPoint() {
      return getLocation();
   }

/**
 * Sets the end point of the line to the point
 * (<code>x</code>,&nbsp;<code>y</code>).
 * The origin of the line remains unchanged.
 *
 * @param x The new x-coordinate of the end point
 * @param y The new y-coordinate of the end point
 */

   public void setEndPoint(double x, double y) {
      GPoint pt = getITM().transform(x - getX(), y - getY());
      dx = pt.getX();
      dy = pt.getY();
      repaint();
   }

/**
 * Returns the end point of the line as a <code>GPoint</code> object.
 *
 * @return The coordinates of the end point of the line
 */

   public GPoint getEndPoint() {
      GPoint pt = getCTM().transform(dx, dy);
      return new GPoint(getX() + pt.getX(), getY() + pt.getY());
   }

/**
 * Returns a string indicating the parameters of this object.
 */

   @Override
   public String paramString() {
      String tail = super.paramString();
      tail = tail.substring(tail.indexOf(')') + 1);
      GPoint pt = getStartPoint();
      String param = "start=(" + pt.getX() + ", " + pt.getY() + ")";
      pt = getEndPoint();
      param += ", end=(" + pt.getX() + ", " + pt.getY() + ")";
      return param + tail;
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      GRectangle bb = new GRectangle(ctm.transform(0, 0));
      bb.add(ctm.transform(dx, dy));
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.  For the
 * <code>GLine</code> class, containment is defined to mean that the
 * point is within <a href="#LINE_TOLERANCE"><code>LINE_TOLERANCE</code></a>
 * pixels of the line.
 */

   @Override
   protected boolean localContains(double x, double y) {
      double tSquared = LINE_TOLERANCE * LINE_TOLERANCE;
      if (distanceSquared(x, y, 0, 0) < tSquared) return true;
      if (distanceSquared(x, y, dx, dy) < tSquared) return true;
      if (x < Math.min(0, dx) - LINE_TOLERANCE) return false;
      if (x > Math.max(0, dx) + LINE_TOLERANCE) return false;
      if (y < Math.min(0, dy) - LINE_TOLERANCE) return false;
      if (y > Math.max(0, dy) + LINE_TOLERANCE) return false;
      if ((float) dx == 0 && (float) dy == 0) return false;
      double u = (x * dx + y * dy) / distanceSquared(0, 0, dx, dy);
      return distanceSquared(x, y, u * dx, u * dy) < tSquared;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      Line2D line = new Line2D.Double(0, 0, GMath.round(dx), GMath.round(dy));
      g.draw(line);
   }

/* Private methods */

/**
 * Returns the square of the distance between
 * (<code>x0</code>,&nbsp;<code>y0</code>) and
 * (<code>x1</code>,&nbsp;<code>y1</code>).
 */

   private double distanceSquared(double x0, double y0, double x1, double y1) {
      return (x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0);
   }

/* Private instance variables */

   private double dx;
   private double dy;

}
