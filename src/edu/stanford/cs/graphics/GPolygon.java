/*
 * File: GPolygon.java
 * -------------------
 * This file exports a GObject subclass that draws a polygon.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * The <code>GPolygon</code> class is a graphical object whose appearance
 * consists of a polygon.
 */

public class GPolygon extends GObject implements GFillable {

/**
 * Constructs a new empty polygon at the origin.
 */

   public GPolygon() {
      vertices = new VertexList();
      clear();
   }

/**
 * Constructs a new empty polygon at (<code>x</code>, <code>y</code>).
 *
 * @param x The x-coordinate of the origin of the polygon
 * @param y The y-coordinate of the origin of the polygon
 */

   public GPolygon(double x, double y) {
      this();
      setLocation(x, y);
   }

/**
 * Constructs a new polygon from the specified array of <code>GPoint</code>
 * objects.  The polygon is automatically marked as complete.
 *
 * @param points An array of <code>GPoint</code> objects specifying the
 *               vertices
 */

   public GPolygon(GPoint[] points) {
      this();
      vertices.add(points);
   }

/**
 * Adds a vertex at (<code>x</code>, <code>y</code>) relative to the
 * polygon origin.
 *
 * @param x The x-coordinate of the vertex relative to the polygon origin
 * @param y The y-coordinate of the vertex relative to the polygon origin
 */

   public void addVertex(double x, double y) {
      vertices.addVertex(x, y);
   }

/**
 * Adds an edge to the polygon whose components are given by the displacements
 * <code>dx</code> and <code>dy</code> from the last vertex.
 *
 * @param dx The x displacement through which the edge moves
 * @param dy The y displacement through which the edge moves
 */

   public void addEdge(double dx, double dy) {
      vertices.addEdge(dx, dy);
   }

/**
 * Adds an edge to the polygon specified in polar coordinates.  The length
 * of the edge is given by <code>r</code>, and the edge extends in
 * direction <code>theta</code>, measured in degrees counterclockwise from
 * the +<i>x</i> axis.
 *
 * @param r The length of the edge
 * @param theta The angle at which the edge extends measured in degrees
 */

   public final void addPolarEdge(double r, double theta) {
      vertices.addEdge(r * GMath.cosDegrees(theta),
                       -r * GMath.sinDegrees(theta));
   }

/**
 * Adds a series of edges to the polygon that simulates the arc specified by
 * the parameters.  The <i>x</i> and <i>y</i> parameters for the arc bounding
 * box are computed implicitly by figuring out what values would place the
 * current vertex at the starting position.
 *
 * @param arcWidth The width of the oval from which the arc is taken
 * @param arcHeight The height of the oval from which the arc is taken
 * @param start The angle at which the arc begins
 * @param sweep The extent of the arc
 */

   public void addArc(double arcWidth, double arcHeight,
                      double start, double sweep) {
      vertices.addArc(arcWidth, arcHeight, start, sweep);
   }

/**
 * Returns the coordinates of the last vertex added to the polygon,
 * or <code>null</code> if the polygon is empty.
 *
 * @return The last vertex added to the polygon, or <code>null</code> if empty
 */

   public GPoint getCurrentPoint() {
      return vertices.getCurrentPoint();
   }

/**
 * Rotates the polygon around its origin by the angle theta, measured in
 * degrees.
 *
 * @param theta The angle of rotation in degrees counterclockwise
 */

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

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      int nPoints = vertices.size();
      if (nPoints == 0) return new GRectangle();
      GRectangle bb = new GRectangle(ctm.transform(vertices.get(0)));
      for (int i = 1; i < nPoints; i++) {
         bb.add(ctm.transform(vertices.get(i)));
      }
      return bb;
   }

/**
 * Checks to see whether a point is inside the object.
 */

   @Override
   protected boolean localContains(double x, double y) {
      int nPoints = vertices.size();
      boolean isContained = false;
      for (int i = 0; i < nPoints; i++) {
         GPoint v1 = vertices.get(i);
         GPoint v2 = vertices.get((i + 1) % nPoints);
         if (((v1.getY() < y) && (v2.getY() >= y))
               || ((v2.getY() < y) && (v1.getY() >= y))) {
            double t = (y - v1.getY()) / (v2.getY() - v1.getY());
            double xp = v1.getX() + t * (v2.getX() - v1.getX());
            if (xp < x) isContained = !isContained;
         }
      }
      return isContained;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      int nPoints = vertices.size();
      Path2D path = new Path2D.Double(Path2D.WIND_EVEN_ODD);
      path.moveTo(vertices.get(0).getX(), vertices.get(0).getY());
      for (int i = 0; i < nPoints; i++) {
         path.lineTo(vertices.get(i).getX(), vertices.get(i).getY());
      }
      path.lineTo(vertices.get(0).getX(), vertices.get(0).getY());
      if (isFilled()) {
         g.setColor(getFillColor());
         g.fill(path);
         g.setColor(getColor());
      }
      g.draw(path);
   }

   @Override
   public Object clone() {
      try {
         GPolygon clone = (GPolygon) super.clone();
         clone.vertices = new VertexList(clone.vertices);
         return clone;
      } catch (Exception CloneNotSupportedException) {
         throw new RuntimeException("Impossible exception");
      }
   }

/**
 * Calling this method deletes all vertices from the polygon.  Subclasses
 * can use this method to reconstruct a polygon.
 */

   protected void clear() {
      vertices.clear();
   }

/* Private instance variables */

   private VertexList vertices;
   private boolean isFilled;
   private Color fillColor;

}

/**
 * The <code>VertexList<code> class represents a list of vertices.
 */

class VertexList {

/**
 * Creates a new <code>VertexList</code> with no elements.
 */

   public VertexList() {
      vertices = new ArrayList<GPoint>();
      cx = 0;
      cy = 0;
   }

/**
 * Creates a new <code>VertexList</code> that is a clone of the old one.
 */

   public VertexList(VertexList oldList) {
      this();
      for (int i = 0; i < oldList.vertices.size(); i++) {
         vertices.add(oldList.vertices.get(i));
      }
   }

/**
 * Adds the specified vertex to the end of the list.
 */

   public synchronized void addVertex(double x, double y) {
      cx = x;
      cy = y;
      vertices.add(new GPoint(cx, cy));
   }

/**
 * Adds the specified edge to the end of the list.
 */

   public synchronized void addEdge(double dx, double dy) {
      cx += dx;
      cy += dy;
      vertices.add(new GPoint(cx, cy));
   }

/**
 * Adds a series of edges to the polygon that simulates the arc specified by
 * the parameters.  The <i>x</i> and <i>y</i> parameters for the arc bounding
 * box are computed implicitly by figuring out what values would place the
 * current vertex at the starting position.
 */

   public void addArc(double arcWidth, double arcHeight,
                      double start, double sweep) {
      double aspectRatio = arcHeight / arcWidth;
      double rx = arcWidth / 2.0;
      double ry = arcHeight / 2.0;
      double x0 = cx - rx * GMath.cosDegrees(start);
      double y0 = cy + ry * GMath.sinDegrees(start);
      if (sweep > 359.99) sweep = 360;
      if (sweep < -359.99) sweep = -360;
      double dt = Math.atan2(1, Math.max(arcWidth, arcHeight));
      int nSteps = (int) (GMath.toRadians(Math.abs(sweep)) / dt);
      dt = GMath.toRadians(sweep) / nSteps;
      double theta = GMath.toRadians(start);
      for (int i = 0; i < nSteps; i++) {
         theta += dt;
         double px = x0 + rx * Math.cos(theta);
         double py = y0 - rx * Math.sin(theta) * aspectRatio;
         addVertex(px, py);
      }
   }

/**
 * Adds copies of the points to the end of the vertex list.
 */

   public synchronized void add(GPoint[] array) {
      for (int i = 0; i < array.length; i++) {
         vertices.add(new GPoint(array[i].getX(), array[i].getY()));
      }
   }

/**
 * Removes the specified vertex from the list.
 */

   public synchronized void remove(GPoint vertex) {
      vertices.remove(vertex);
   }

/**
 * Removes all vertices from the list.
 */

   public synchronized void clear() {
      vertices.clear();
   }

/**
 * Returns the number of vertices in the list.
 */

   public int size() {
      return vertices.size();
   }

/**
 * Returns the point at the specified index.
 */

   GPoint get(int index) {
      return vertices.get(index);
   }

/**
 * Returns the coordinates of the last vertex added to the polygon,
 * or <code>null</code> if the polygon is empty.
 */

   public GPoint getCurrentPoint() {
      return (vertices.size() == 0) ? null : new GPoint(cx, cy);
   }

/* Private instance variables */

   private ArrayList<GPoint> vertices;
   private double cx;
   private double cy;

};
