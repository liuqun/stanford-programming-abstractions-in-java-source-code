/*
 * File: GMath.java
 * ----------------
 * This file exports several static methods for graphical calculations.
 */

package edu.stanford.cs.graphics;

/**
 * This class defines a variety of static mathematical methods that are
 * useful for graphical calculations.
 */

public class GMath {

/* Prevent clients from instantiating this class */

   private GMath() {
      /* Empty */
   }

/**
 * Rounds a <code>double</code> value to the nearest <code>int</code>.
 *
 * @param x A <code>double</code> value
 * @return The nearest <code>int</code> value
 */

   public static int round(double x) {
      return (int) Math.round(x);
   }

/**
 * Returns the trigonometric sine of its argument where <code>angle</code>
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric sine of the angle
 */

   public static double sinDegrees(double angle) {
      return Math.sin(toRadians(angle));
   }

/**
 * Returns the trigonometric cosine of its argument where <code>angle</code>
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric cosine of the angle
 */

   public static double cosDegrees(double angle) {
      return Math.cos(toRadians(angle));
   }

/**
 * Returns the trigonometric tangent of its argument where <code>angle</code>
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric tangent of the angle
 */

   public static double tanDegrees(double angle) {
      return sinDegrees(angle) / cosDegrees(angle);
   }

/**
 * Converts an angle from radians to degrees.  This method is defined in
 * the <code>Math</code> class, but was added only in JDK1.2, which is not
 * supported in all browsers.
 *
 * @param radians An angle measured in radians
 * @return The equivalent angle in degrees
 */

   public static double toDegrees(double radians) {
      return radians * 180 / Math.PI;
   }

/**
 * Converts an angle from degrees to radians.  This method is defined in
 * the <code>Math</code> class, but was added only in JDK1.2, which is not
 * supported in all browsers.
 *
 * @param degrees An angle measured in degrees
 * @return The equivalent angle in radians
 */

   public static double toRadians(double degrees) {
      return degrees * Math.PI / 180;
   }

/**
 * Computes the distance between the origin and the point
 * (<code>x</code>,&nbsp;<code>y</code>).
 *
 * @param x The x-coordinate of the point
 * @param y The y-coordinate of the point
 * @return The distance from the origin to the point
 *         (<code>x</code>,&nbsp;<code>y</code>)
 */

   public static double distance(double x, double y) {
      return Math.sqrt(x * x + y * y);
   }

/**
 * Computes the distance between the points
 * (<code>x0</code>,&nbsp;<code>y0</code>) and
 * (<code>x1</code>,&nbsp;<code>y1</code>).
 *
 * @param x0 The x-coordinate of one point
 * @param y0 The y-coordinate of that point
 * @param x1 The x-coordinate of the other point
 * @param y1 The y-coordinate of that point
 * @return The distance between the points
 *         (<code>x0</code>,&nbsp;<code>y0</code>) and
 *         (<code>x1</code>,&nbsp;<code>y1</code>)
 */

   public static double distance(double x0, double y0, double x1, double y1) {
      return distance(x1 - x0, y1 - y0);
   }

/**
 * Returns the angle in degrees from the origin to the point
 * (<code>x</code>,&nbsp;<code>y</code>).  This method is easier to use than
 * <code>atan2</code> because it specifies the displacements in the usual
 * x/y order and because it takes care of the fact that the Java coordinate
 * system is flipped.  The point (0, 0) is arbitrarily defined to be at
 * angle 0.
 *
 * @param x The x-coordinate of the point
 * @param y The y-coordinate of the point
 * @return The angle from the origin to the point
 *         (<code>x</code>,&nbsp;<code>y</code>) measured in degrees
 *         counterclockwise from the +<i>x</i> axis
 */

   public static double angle(double x, double y) {
      if (x == 0 && y == 0) return 0;
      return toDegrees(Math.atan2(-y, x));
   }

/**
 * Computes the angle in degrees formed by a line segment from the
 * point (<code>x0</code>,&nbsp;<code>y0</code>) and
 * (<code>x1</code>,&nbsp;<code>y1</code>).
 *
 * @param x0 The x-coordinate of one point
 * @param y0 The y-coordinate of that point
 * @param x1 The x-coordinate of the other point
 * @param y1 The y-coordinate of that point
 * @return The angle formed by the line segment from
 *         (<code>x0</code>,&nbsp;<code>y0</code>) to
 *         (<code>x1</code>,&nbsp;<code>y1</code>)
 */

   public static double angle(double x0, double y0, double x1, double y1) {
      return angle(x1 - x0, y1 - y0);
   }

}
