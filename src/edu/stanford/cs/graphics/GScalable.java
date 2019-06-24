/*
 * File: GScalable.java
 * --------------------
 * This interface defines the behavior of GObjects that can be scaled.
 */

package edu.stanford.cs.graphics;

/**
 * Specifies the characteristics of a graphical object that supports the
 * <code>scale</code> method.  This interface is included for historical
 * reasons, given that scaling now applies to all GObject subclasses.
 */

public interface GScalable {

/**
 * Scales the object on the screen by the scale factors <code>sx</code>
 * and <code>sy</code>.
 *
 * @param sx The factor used to scale all coordinates in the x direction
 * @param sy The factor used to scale all coordinates in the y direction
 */

   public void scale(double sx, double sy);

/**
 * Scales the object on the screen by the scale factor <code>sf</code>,
 * which applies in both dimensions.
 *
 * @param sf The factor used to scale all coordinates in both dimensions
 */

   public void scale(double sf);

}
