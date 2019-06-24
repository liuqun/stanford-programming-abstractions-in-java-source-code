/*
 * File: G3DRect.java
 * ------------------
 * This file exports a GRect subclass that adds 3-D shading.
 */

package edu.stanford.cs.graphics;

import java.awt.Graphics2D;
import java.awt.Graphics;

/**
 * The <code>G3DRect</code> class is used to represent a rectangle whose
 * borders are drawn to create a three-dimensional effect.  The
 * <code>G3DRect</code> class is a subclass of
 * <a href="GRect.html"><code>GRect</code></a>, and therefore
 * implements all the methods defined for that class.  In addition, the
 * <code>G3DRect</code> class supports the following methods:
 *
 * <p><table cellpadding=0 cellspacing=0 border=0><tr>
 * <td width=20>&nbsp;</td>
 * <td><table cellpadding=2 cellspacing=0 border=1>
 * <tr><td><code>void setRaised(boolean raised)</code><br>
 * <code>&nbsp;&nbsp;&nbsp;</code>Sets the <code>G3DRect</code> to appear
 * raised or lowered as specified by the parameter</td></tr>
 * <tr><td><code>boolean isRaised()</code><br>
 * <code>&nbsp;&nbsp;&nbsp;</code>Returns <code>true</code> if this
 * <code>G3DRect</code> is raised.</td></tr>
 * </table></td></tr></table>
 * <p>The appearance of a <code>G3DRect</code> object depends on the system on
 * which it is displayed and is typically more effective if the
 * <code>G3DRect</code> is filled.
 */

public class G3DRect extends GRect {

/**
 * Constructs a new 3D rectangle with the specified width and height,
 * positioned at the origin.
 *
 * @param width The width of the rectangle in pixels
 * @param height The height of the rectangle in pixels
 */

   public G3DRect(double width, double height) {
      this(0, 0, width, height, false);
   }

/**
 * Constructs a new 3D rectangle with the specified bounds.
 *
 * @param x The x-coordinate of the upper left corner
 * @param y The y-coordinate of the upper left corner
 * @param width The width of the rectangle in pixels
 * @param height The height of the rectangle in pixels
 */

   public G3DRect(double x, double y, double width, double height) {
      this(x, y, width, height, false);
   }

/**
 * Constructs a new 3D rectangle with the specified bounds which is
 * raised if the final parameter is <code>true</code>.
 *
 * @param x The x-coordinate of the upper left corner
 * @param y The y-coordinate of the upper left corner
 * @param width The width of the rectangle in pixels
 * @param height The height of the rectangle in pixels
 * @param raised <code>true</code> if this rectangle should appear raised
 */

   public G3DRect(double x, double y, double width, double height,
                                      boolean raised) {
      super(x, y, width, height);
      isRaised = raised;
   }

/**
 * Implements the <code>paint</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   public void paint(Graphics g) {
      if (!isVisible()) return;
      Graphics2D g2d = createTransformedGraphics(g);
      if (isFilled()) {
         g2d.setColor(getFillColor());
         g2d.fill3DRect(0, 0, GMath.round(getFrameWidth()),
                              GMath.round(getFrameHeight()), isRaised);
         g2d.setColor(getColor());
      }
      g2d.draw3DRect(0, 0, GMath.round(getFrameWidth()),
                           GMath.round(getFrameHeight()), isRaised);
      g2d.dispose();
   }

/**
 * Sets whether this object appears raised.
 *
 * @param raised <code>true</code> if the object appears raised,
 *               <code>false</code> otherwise
 */

   public void setRaised(boolean raised) {
      isRaised = raised;
   }

/**
 * Tests whether this object appears raised.
 *
 * @return <code>true</code> if the object appears raised,
 *         <code>false</code> otherwise
 */

   public boolean isRaised() {
      return isRaised;
   }

/* Private instance variables */

   private boolean isRaised;

}
