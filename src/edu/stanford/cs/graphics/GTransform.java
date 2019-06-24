/*
 * File: GTransform.java
 * ---------------------
 * This class is a simplified version of the GTransform class in
 * java.awt.geom.  This subset has the following advantages over the
 * standard class:
 *
 * 1. It is easier to reimplement in JavaScript and C++.
 * 2. It uses a functional rather than a procedural model for transforms.
 * 3. It works with the opaque GPoint class.
 */

package edu.stanford.cs.graphics;

/**
 * This class implements a subset of <code>GTransform</code>.
 */

public class GTransform {

/**
 * Creates a new <code>GTransform</code> equal to the identity matrix.
 */

   public GTransform() {
      setTransform(1, 0, 0, 1, 0, 0);
   }

/**
 * Creates a copy of the specified <code>GTransform</code>.
 *
 * @param t The original <code>GTransform</code>
 */

   public GTransform(GTransform t) {
      setTransform(t.a, t.b, t.c, t.d, t.tx, t.ty);
   }

/**
 * Creates a <code>GTransform</code> from the scale and shear entries.
 *
 * @param a The scaleX entry
 * @param b The shearY entry
 * @param c The shearX entry
 * @param d The scaleY entry
 */

   public GTransform(double a, double b, double c, double d) {
      setTransform(a, b, c, d, 0, 0);
   }

/**
 * Creates a <code>GTransform</code> from all six matrix components.
 *
 * @param a The scaleX entry
 * @param b The shearY entry
 * @param c The shearX entry
 * @param d The scaleY entry
 * @param tx The translateX entry
 * @param ty The translateY entry
 */

   public GTransform(double a, double b, double c, double d,
                     double tx, double ty) {
      setTransform(a, b, c, d, tx, ty);
   }

/**
 * Concatenates the specified transform onto this matrix.
 *
 * @param t The new <code>GTransform</code> matrix
 */

   public void concatenate(GTransform t) {
      setTransform(a * t.a + c * t.b,
                   b * t.a + d * t.b,
                   a * t.c + c * t.d,
                   b * t.c + d * t.d,
                   a * t.tx + c * t.ty + tx,
                   b * t.tx + d * t.ty + ty);
   }

/**
 * Creates and returns an inverse of the current transform.  This method
 * throws a runtime exception if the matrix is not invertible.
 *
 * @return An inverse of the current transform
 */

   public GTransform createInverse() {
      double det = getDeterminant();
      if (det == 0) throw new RuntimeException("Noninvertible transform");
      return new GTransform(d / det, -b / det,
                            -c / det, a / det,
                            (c * ty - tx * d) / det,
                            (b * tx - a * ty) / det);
   }

/**
 * Returns the determinant of the current transform.
 *
 * @return The determinant of the current transform
 */

   public double getDeterminant() {
      return a * d - b * c;
   }

/**
 * Returns the <code>scaleX</code> component of the matrix.
 *
 * @return The <code>scaleX</code> component of the matrix
 */

   public double getScaleX() {
      return a;
   }

/**
 * Returns the <code>scaleY</code> component of the matrix.
 *
 * @return The <code>scaleY</code> component of the matrix
 */

   public double getScaleY() {
      return d;
   }

/**
 * Returns the <code>shearX</code> component of the matrix.
 *
 * @return The <code>shearX</code> component of the matrix
 */

   public double getShearX() {
      return c;
   }

/**
 * Returns the <code>shearY</code> component of the matrix.
 *
 * @return The <code>shearY</code> component of the matrix
 */

   public double getShearY() {
      return b;
   }

/**
 * Returns the <code>translateX</code> component of the matrix.
 *
 * @return The <code>translateX</code> component of the matrix
 */

   public double getTranslateX() {
      return tx;
   }

/**
 * Returns the <code>translateY</code> component of the matrix.
 *
 * @return The <code>translateY</code> component of the matrix
 */

   public double getTranslateY() {
      return ty;
   }

/**
 * Applies the inverse transform to the point <code>pt</code>.
 *
 * @param pt The original point
 * @return The inverse transformed point
 */

   public GPoint inverseTransform(GPoint pt) {
      return createInverse().transform(pt);
   }

/**
 * Applies the inverse transform to the point (<code>x</code>, <code>y</code>).
 *
 * @param x The original x coordinate
 * @param y The original y coordinate
 * @return The inverse transformed point
 */

   public GPoint inverseTransform(double x, double y) {
      return createInverse().transform(x, y);
   }
   
/**
 * Concatenates a rotation transformation through the angle
 * <code>theta</code>, measured in degrees counterclockwise
 * from the +<i>x</i> axis.
 *
 * @param theta The angle of rotation in degrees
 */

   public void rotate(double theta) {
      double cosTheta = GMath.cosDegrees(-theta);
      double sinTheta = GMath.sinDegrees(-theta);
      if (Math.abs(cosTheta) < ZERO_RADIUS) cosTheta = 0;
      if (Math.abs(sinTheta) < ZERO_RADIUS) sinTheta = 0;
      setTransform(a * cosTheta + c * sinTheta,
                   b * cosTheta + d * sinTheta,
                   c * cosTheta - a * sinTheta,
                   d * cosTheta - b * sinTheta, tx, ty);
   }

/**
 * Scales the transformation by the scale factors <code>sx</code> and
 * <code>sy</code>.
 *
 * @param sx The scale factor in the x direction
 * @param sy The scale factor in the y direction
 */

   public void scale(double sx, double sy) {
      a *= sx;
      b *= sx;
      c *= sy;
      d *= sy;
   }

/**
 * Sets the transform to the specified values.
 *
 * @param a The scaleX entry
 * @param b The shearY entry
 * @param c The shearX entry
 * @param d The scaleY entry
 * @param tx The translateX entry
 * @param ty The translateY entry
 */

   public void setTransform(double a, double b, double c, double d,
                            double tx, double ty) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.tx = tx;
      this.ty = ty;
   }

/**
 * Concatenates the transformation with a shear transformation with the
 * given components.
 *
 * @param sx The shear factor in the x direction
 * @param sy The shear factor in the y direction
 */

   public void shear(double sx, double sy) {
      setTransform(a + (sy * c), c + (sx * a),
                   b + (sy * d), d + (sx * b), tx, ty);
   }

/**
 * Applies this transform matrix to the point <code>pt</code>.
 *
 * @param pt The original point
 * @return The transformed point
 */

   public GPoint transform(GPoint pt) {
      return transform(pt.getX(), pt.getY());
   }

/**
 * Applies this transform matrix to the point (<code>x</code>, <code>y</code>).
 *
 * @param x The original x coordinate
 * @param y The original y coordinate
 * @return The transformed point
 */

   public GPoint transform(double x, double y) {
      return new GPoint(a * x + c * y + tx, b * x + d * y + ty);
   }

/**
 * Concatenates this transform with a translation by the specified offsets.
 *
 * @param tx The translation offset in the x direction
 * @param ty The translation offset in the y direction
 */

   public void translate(double tx, double ty) {
      this.tx += tx * a + ty * c;
      this.ty += tx * b + ty * d;
   }

/**
 * Converts this transform to a string.
 *
 * @return The string version of this transform
 */

   @Override
   public String toString() {
      return "[" + a + ", " + b + ", " + c + ", " + d + ", " +
                   tx + ", " + ty + "]";
   }

/* Private constants */

   private static final double ZERO_RADIUS = 1.0E-10;

/* Private instance variables */

   private double a;                        /* The scaleX entry     */
   private double b;                        /* The shearY entry     */
   private double c;                        /* The shearX entry     */
   private double d;                        /* The scaleY entry     */
   private double tx;                       /* The translateX entry */
   private double ty;                       /* The translateY entry */

}
