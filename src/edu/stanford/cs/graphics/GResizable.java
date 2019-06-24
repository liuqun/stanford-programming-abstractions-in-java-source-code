/*
 * File: GResizable.java
 * ---------------------
 * This interface defines the behavior of GObjects that can be resized.
 */

package edu.stanford.cs.graphics;

/**
 * Specifies the characteristics of a graphical object that supports the
 * <code>setSize</code> and <code>setBounds</code> methods.
 */

public interface GResizable {

/**
 * Changes the size of the object so that it has the specified width and
 * height.  This method throws a runtime exception if the object has been
 * rotated.
 *
 * @param width The new width of the object
 * @param height The new height of the object
 */

   public void setSize(double width, double height);

/**
 * Changes the size of this object to match that of the specified
 * <code>GDimension</code> object.  This method throws a runtime
 * exception if the object has been transformed.
 *
 * @param size A <code>GDimension</code> object specifying the new size
 */

   public void setSize(GDimension size);

/**
 * Changes the bounds of this object to the specified values.  This method
 * throws a runtime exception if the object has been transformed.
 *
 * @param x The new x-coordinate for the object
 * @param y The new y-coordinate for the object
 * @param width The new width of the object
 * @param height The new height of the object
 */

   public void setBounds(double x, double y, double width, double height);

/**
 * Changes the bounds of this object to match the specified
 * <code>GRectangle</code>.  This method throws a runtime exception if
 * the object has been transformed.
 *
 * @param bounds A <code>GRectangle</code> specifying the new bounds
 */

   public void setBounds(GRectangle bounds);

}
