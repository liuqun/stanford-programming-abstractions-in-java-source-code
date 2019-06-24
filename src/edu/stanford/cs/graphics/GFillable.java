/*
 * File: GFillable.java
 * --------------------
 * This interface defines the operations on fillable objects.
 */

package edu.stanford.cs.graphics;

import java.awt.Color;

/**
 * Specifies the characteristics of a graphical object that supports filling.
 */

public interface GFillable {

/**
 * Sets whether this object is filled.
 *
 * @param fill <code>true</code> if the object should be filled,
 *             <code>false</code> for an outline
 */

   public void setFilled(boolean fill);

/**
 * Returns whether this object is filled.
 *
 * @return The color used to display the object
 */

   public boolean isFilled();

/**
 * Sets the color used to display the filled region of this object.
 *
 * @param color The color used to display the filled region of this object
 */

   public void setFillColor(Color color);

/**
 * Returns the color used to display the filled region of this object.  If
 * none has been set, <code>getFillColor</code> returns the color of the
 * object.
 *
 * @return The color used to display the filled region of this object
 */

   public Color getFillColor();

}
