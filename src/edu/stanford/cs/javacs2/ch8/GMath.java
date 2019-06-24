/*
 * File: GMath.java
 * ----------------
 * This file exports the GMath class, which exports several static methods
 * designed to simplify graphical calculations.
 */

package edu.stanford.cs.javacs2.ch8;

/**
 * This class defines a variety of static mathematical methods that are
 * useful when writing graphical programs.
 */

public class GMath {

/**
 * Rounds a double value to the nearest int.
 *
 * @param x A double value
 * @return The nearest int value
 */

   public static int round(double x) {
      return (int) Math.round(x);
   }

/**
 * Returns the trigonometric sine of its argument where angle
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric sine of the angle
 */

   public static double sinDegrees(double angle) {
      return Math.sin(toRadians(angle));
   }

/**
 * Returns the trigonometric cosine of its argument where angle
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric cosine of the angle
 */

   public static double cosDegrees(double angle) {
      return Math.cos(toRadians(angle));
   }

/**
 * Returns the trigonometric tangent of its argument where angle
 * is expressed in degrees.
 *
 * @param angle An angle measured in degrees
 * @return The trigonometric tangent of the angle
 */

   public static double tanDegrees(double angle) {
      return sinDegrees(angle) / cosDegrees(angle);
   }

/**
 * Converts an angle from radians to degrees.
 *
 * @param radians An angle measured in radians
 * @return The equivalent angle in degrees
 */

   public static double toDegrees(double radians) {
      return radians * 180 / Math.PI;
   }

/**
 * Converts an angle from degrees to radians.
 *
 * @param degrees An angle measured in degrees
 * @return The equivalent angle in radians
 */

   public static double toRadians(double degrees) {
      return degrees * Math.PI / 180;
   }

/**
 * Computes the distance between the origin and the point (x, y).
 *
 * @param x The x-coordinate of the point
 * @param y The y-coordinate of the point
 * @return The distance from the origin to the point
 *         (x, y)
 */

   public static double distance(double x, double y) {
      return Math.sqrt(x * x + y * y);
   }

/**
 * Computes the distance between the points (x0, y0) and (x1, y1).
 *
 * @param x0 The x-coordinate of one point
 * @param y0 The y-coordinate of that point
 * @param x1 The x-coordinate of the other point
 * @param y1 The y-coordinate of that point
 * @return The distance between the points
 *         (x0, y0) and
 *         (x1, y1)
 */

   public static double distance(double x0, double y0, double x1, double y1) {
      return distance(x1 - x0, y1 - y0);
   }

/**
 * Returns the angle in degrees from the origin to the point (x, y).  This
 * method is easier to use than atan2 because it specifies the displacements
 * in the usual x/y order and because it takes care of the fact that the
 * Java coordinate system is flipped.  The point (0, 0) is arbitrarily
 * defined to be at angle 0.
 *
 * @param x The x-coordinate of the point
 * @param y The y-coordinate of the point
 * @return The angle from the origin to the point (x, y) measured in degrees
 *         counterclockwise from the +x axis
 */

   public static double angle(double x, double y) {
      if (x == 0 && y == 0) return 0;
      return toDegrees(Math.atan2(-y, x));
   }

/**
 * Computes the angle in degrees formed by a line segment from the
 * point (x0, y0) and (x1, y1).
 *
 * @param x0 The x-coordinate of one point
 * @param y0 The y-coordinate of that point
 * @param x1 The x-coordinate of the other point
 * @param y1 The y-coordinate of that point
 * @return The angle formed by the line segment from
 *         (x0, y0) to
 *         (x1, y1)
 */

   public static double angle(double x0, double y0, double x1, double y1) {
      return angle(x1 - x0, y1 - y0);
   }

}
