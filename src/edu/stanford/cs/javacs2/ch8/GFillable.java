/*
 * File: GFillable.java
 * --------------------
 * This file exports the GFillable interface that marks objects as fillable.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;

public interface GFillable {

/**
 * Sets whether this object is filled.
 */

   public void setFilled(boolean fill);

/**
 * Sets the color used to display the filled region of this object.
 */

   public void setFillColor(Color color);

}
