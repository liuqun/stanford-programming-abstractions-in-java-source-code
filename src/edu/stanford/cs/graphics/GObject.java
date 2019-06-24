/*
 * File: GObject.java
 * ------------------
 * This file exports the GObject class, which is the root of the hierarchy.
 */

package edu.stanford.cs.graphics;

import java.awt.AWTEventMulticaster;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Method;

/**
 * This class is the common superclass of all graphical objects that can
 * be displayed on a <a href="GCanvas.html"><code>GCanvas</code></a>.
 * Because it is an abstract class, you are not allowed to construct an
 * object whose class is <code>GObject</code> directly.  What you do
 * instead is construct one of the concrete subclasses like
 * <a href="GRect.html"><code>GRect</code></a> or
 * <a href="GLine.html"><code>GLine</code></a>.
 * The purpose of this class definition is to define methods that apply
 * to all graphical objects regardless of their specific class.
 */

public abstract class GObject implements Cloneable, GScalable {

/**
 * Constructs a new <code>GObject</code> and initializes its state.  This
 * constructor is never called explicitly, but is instead invoked by the
 * constructors of its subclasses.
 */

   protected GObject() {
      ctm = new GTransform();
      itm = null;
      lineWidth = 1;
      isVisible = true;
      transformed = false;
      mouseListenersEnabled = false;
   }

/**
 * This method paints the object on the graphics context.
 */

   public void paint(Graphics g) {
      if (isVisible) {
         Graphics2D g2d = createTransformedGraphics(g);
         paint2d(g2d);
         g2d.dispose();
      }
   }


/**
 * Returns the bounding box of this object, which is defined to be the
 * smallest rectangle that covers everything drawn by the figure.  The
 * coordinates of this rectangle do not necessarily match the location
 * returned by <a href="#getLocation()"><code>getLocation</code></a>.
 * Given a <a href="GLabel.html"><code>GLabel</code></a> object, for
 * example, <a href="#getLocation()"><code>getLocation</code></a>
 * returns the coordinates of the point on the baseline at which the
 * string begins; <code>getBounds</code>, by contrast, returns a
 * rectangle that covers the entire window area occupied by the string.
 *
 * @return The bounding box for this object
 */

   public GRectangle getBounds() {
      GRectangle r = localBounds(ctm);
      r.translate(x, y);
      return r;
   }      

/**
 * Sets the location of this object to the point
 * (<code>x</code>, <code>y</code>).
 *
 * @param x The new x-coordinate for the object
 * @param y The new y-coordinate for the object
 */

   public void setLocation(double x, double y) {
      this.x = x;
      this.y = y;
      repaint();
   }

/**
 * Sets the location of this object to the specified point.
 *
 * @param pt The new location for this object
 */

   public final void setLocation(GPoint pt) {
      setLocation(pt.getX(), pt.getY());
   }

/**
 * Returns the location of this object as a <code>GPoint</code>.
 *
 * @return The location of this object as a <code>GPoint</code>
 */

   public GPoint getLocation() {
      return new GPoint(x, y);
   }

/**
 * Returns the x-coordinate of the object.
 *
 * @return The x-coordinate of the object
 */

   public double getX() {
      return x;
   }

/**
 * Returns the y-coordinate of the object.
 *
 * @return The y-coordinate of the object
 */

   public double getY() {
      return y;
   }

/**
 * Moves the object on the screen using the displacements
 * <code>dx</code> and <code>dy</code>.
 *
 * @param dx The distance to move the object in the x direction
 *           (positive is rightward)
 * @param dy The distance to move the object in the y direction
 *           (positive is downward)
 */

   public void move(double dx, double dy) {
      setLocation(x + dx, y + dy);
   }

/**
 * Moves the object using displacements given in polar coordinates.  The
 * parameter <code>r</code> specifies the distance to move and
 * <code>theta</code> specifies the angle in which the motion occurs.
 * The angle is measured in degrees increasing counterclockwise from
 * the +<i>x</i> axis.
 *
 * @param r The distance to move
 * @param theta The angle in which to move, measured in degrees
 *              increasing counterclockwise from the +x axis
 */

   public final void movePolar(double r, double theta) {
      double radians = theta * Math.PI / 180;
      move(r * Math.cos(radians), -r * Math.sin(radians));
   }

/**
 * Returns the size of the bounding box for this object.
 *
 * @return The size of this object
 */

   public GDimension getSize() {
      GRectangle bounds = getBounds();
      return new GDimension(bounds.getWidth(), bounds.getHeight());
   }

/**
 * Returns the width of this object, which is defined to be
 * the width of the bounding box.
 *
 * @return The width of this object on the screen
 */

   public double getWidth() {
      return getBounds().getWidth();
   }

/**
 * Returns the height of this object, which is defined to be
 * the height of the bounding box.
 *
 * @return The height of this object on the screen
 */

   public double getHeight() {
      return getBounds().getHeight();
   }

/**
 * Checks to see whether a point is inside the object.
 *
 * @param x The x-coordinate of the point being tested
 * @param y The y-coordinate of the point being tested
 * @return <code>true</code> if the point (<code>x</code>,&nbsp;<code>y</code>)
 *         is inside the object, and <code>false</code> otherwise
 */

   public boolean contains(double x, double y) {
      GTransform t = new GTransform();
      t.translate(this.x, this.y);
      t.concatenate(getCTM());
      GPoint pt = t.inverseTransform(x, y);
      return localContains(pt.getX(), pt.getY());
   }

/**
 * Checks to see whether a point is inside the object.
 *
 * @param pt The point being tested
 * @return <code>true</code> if the point is inside the object,
 *         and <code>false</code> otherwise
 */

   public boolean contains(GPoint pt) {
      return contains(pt.getX(), pt.getY());
   }

/**
 * Moves this object to the front of the display in the <i>z</i> dimension.
 * By moving it to the front, this object will appear to be on top of the
 * other graphical objects on the display and may hide any objects that
 * are further back.
 */

   public void sendToFront() {
      if (compoundParent != null) {
         compoundParent.sendToFront(this);
      } else if (transientParent instanceof GCanvas) {
         ((GCanvas) transientParent).sendToFront(this);
      } else if (transientParent != null) {
         try {
            Class<?> parentClass = transientParent.getClass();
            Class<?>[] types = { Class.forName("acm.graphics.GObject") };
            Object[] args = { this };
            Method fn = parentClass.getMethod("sendToFront", types);
            if (fn != null) fn.invoke(transientParent, args);
         } catch (Exception ex) {
            /* Empty */
         }
      }
      if (mouseListenersEnabled) updateEnabledList();
   }

/**
 * Moves this object to the back of the display in the <i>z</i> dimension.
 * By moving it to the back, this object will appear to be behind the
 * other graphical objects on the display and may be obscured by other
 * objects in front.
 */

   public void sendToBack() {
      if (compoundParent != null) {
         compoundParent.sendToBack(this);
      } else if (transientParent instanceof GCanvas) {
         ((GCanvas) transientParent).sendToBack(this);
      } else if (transientParent != null) {
         try {
            Class<?> parentClass = transientParent.getClass();
            Class<?>[] types = { Class.forName("acm.graphics.GObject") };
            Object[] args = { this };
            Method fn = parentClass.getMethod("sendToBack", types);
            if (fn != null) fn.invoke(transientParent, args);
         } catch (Exception ex) {
            /* Empty */
         }
      }
      if (mouseListenersEnabled) updateEnabledList();
   }

/**
 * Moves this object one step toward the front in the <i>z</i> dimension.
 * If it was already at the front of the stack, nothing happens.
 */

   public void sendForward() {
      if (compoundParent != null) {
         compoundParent.sendForward(this);
      } else if (transientParent instanceof GCanvas) {
         ((GCanvas) transientParent).sendForward(this);
      } else if (transientParent != null) {
         try {
            Class<?> parentClass = transientParent.getClass();
            Class<?>[] types = { Class.forName("acm.graphics.GObject") };
            Object[] args = { this };
            Method fn = parentClass.getMethod("sendForward", types);
            if (fn != null) fn.invoke(transientParent, args);
         } catch (Exception ex) {
            /* Empty */
         }
      }
      if (mouseListenersEnabled) updateEnabledList();
   }

/**
 * Moves this object one step toward the back in the <i>z</i> dimension.
 * If it was already at the back of the stack, nothing happens.
 */

   public void sendBackward() {
      if (compoundParent != null) {
         compoundParent.sendBackward(this);
      } else if (transientParent instanceof GCanvas) {
         ((GCanvas) transientParent).sendBackward(this);
      } else if (transientParent != null) {
         try {
            Class<?> parentClass = transientParent.getClass();
            Class<?>[] types = { Class.forName("acm.graphics.GObject") };
            Object[] args = { this };
            Method fn = parentClass.getMethod("sendBackward", types);
            if (fn != null) fn.invoke(transientParent, args);
         } catch (Exception ex) {
            /* Empty */
         }
      }
      if (mouseListenersEnabled) updateEnabledList();
   }

/**
 * Sets the color used to display this object.
 *
 * @param color The color used to display this object
 */

   public void setColor(Color color) {
      objectColor = color;
      repaint();
   }

/**
 * Returns the color used to display this object.
 *
 * @return The color used to display this object
 */

   public Color getColor() {
      GObject obj = this;
      while (obj.objectColor == null) {
         GContainer parent = obj.getParent();
         if (parent instanceof GObject) {
            obj = (GObject) parent;
         } else if (parent instanceof Component) {
            return ((Component) parent).getForeground();
         } else {
            return Color.BLACK;
         }
      }
      return obj.objectColor;
   }

/**
 * Sets the width of the lines used to draw the object.
 *
 * @param width The line width in pixels
 */

   public void setLineWidth(double width) {
      lineWidth = width;
      repaint();
   }

/**
 * Returns the width of the lines used to draw the object.
 *
 * @return The line width in pixels
 */

   public double getLineWidth() {
      return lineWidth;
   }

/**
 * Rotates the object on the screen <code>theta</code> degrees around
 * its origin.
 *
 * @param theta The angle of rotation in degrees counterclockwise
 */

   public void rotate(double theta) {
      ctm.rotate(theta);
      itm = null;
      transformed = true;
      repaint();
   }

/**
 * Scales the object on the screen by the scale factors
 * <code>sx</code> and <code>sy</code>.
 *
 * @param sx The factor used to scale all coordinates in the x direction
 * @param sy The factor used to scale all coordinates in the y direction
 */

   @Override
   public void scale(double sx, double sy) {
      ctm.scale(sx, sy);
      itm = null;
      transformed = true;
      repaint();
   }

/**
 * Scales the object on the screen by the scale factor <code>sf</code>,
 * which applies in both dimensions.
 *
 * @param sf The factor used to scale all coordinates in both dimensions
 */

   @Override
   public final void scale(double sf) {
      scale(sf, sf);
   }

/**
 * Applies a shear transformation to the object that scales each dimension
 * in proportion to the other.
 *
 * @param sx The x component of the shear transformation
 * @param sy The y component of the shear transformation
 */

   public void shear(double sx, double sy) {
      ctm.shear(sx, sy);
      itm = null;
      transformed = true;
      repaint();
   }

/**
 * Translates this object relative to its origin.  Note that this
 * transformation affects the displayed value and not the value returned
 * by <code>getLocation</code>.
 *
 * @param tx The x component of the translation
 * @param ty The y component of the translation
 */

   public void translate(double tx, double ty) {
      ctm.translate(tx, ty);
      itm = null;
      transformed = true;
      repaint();
   }

/**
 * Sets whether this object is visible.
 *
 * @param visible <code>true</code> to make the object visible,
 *                <code>false</code> to hide it
 */

   public void setVisible(boolean visible) {
      isVisible = visible;
      repaint();
   }

/**
 * Checks to see whether this object is visible.
 *
 * @return <code>true</code> if the object is visible,
 *         otherwise <code>false</code>
 */

   public boolean isVisible() {
      return isVisible;
   }

/**
 * Overrides the <code>toString</code> method in <code>Object</code> to
 * produce more readable output.
 */

   @Override
   public String toString() {
      String name = getClass().getName();
      if (name.startsWith("acm.graphics.")) {
         name = name.substring("acm.graphics.".length());
      }
      return name + "[" + paramString() + "]";
   }

/**
 * Returns the parent of this object, which is the canvas or compound object
 * in which it is enclosed.
 *
 * @return The parent of this object
 */

   public GContainer getParent() {
      return (compoundParent != null) ? compoundParent : transientParent;
   }

/**
 * Adds a mouse listener to this graphical object.
 *
 * @param listener Any object that implements the <code>MouseListener</code>
 *                 interface
 */

   public void addMouseListener(MouseListener listener) {
      mouseListener = AWTEventMulticaster.add(mouseListener, listener);
      mouseListenersEnabled = true;
      updateEnabledList();
   }

/**
 * Removes a mouse listener from this graphical object.
 *
 * @param listener The listener object to remove
 */

   public void removeMouseListener(MouseListener listener) {
      mouseListener = AWTEventMulticaster.remove(mouseListener, listener);
   }

/**
 * Adds a mouse motion listener to this graphical object.
 *
 * @param listener Any object that implements the
 *                 <code>MouseMotionListener</code> interface
 */

   public void addMouseMotionListener(MouseMotionListener listener) {
      mouseMotionListener = AWTEventMulticaster.add(mouseMotionListener,
                                                    listener);
      mouseListenersEnabled = true;
      updateEnabledList();
   }

/**
 * Removes a mouse motion listener from this graphical object.
 *
 * @param listener The listener object to remove
 */

   public void removeMouseMotionListener(MouseMotionListener listener) {
      mouseMotionListener = AWTEventMulticaster.remove(mouseMotionListener,
                                                       listener);
   }

/**
 * Adds an action listener to this graphical object.
 *
 * @param listener Any object that implements the <code>ActionListener</code>
 *                 interface
 */

   public void addActionListener(ActionListener listener) {
      actionListener = AWTEventMulticaster.add(actionListener, listener);
   }

/**
 * Removes an action listener from this graphical object.
 *
 * @param listener The listener object to remove
 */

   public void removeActionListener(ActionListener listener) {
      actionListener = AWTEventMulticaster.remove(actionListener, listener);
   }

/**
 * Triggers an action event for this graphical object with the specified
 * action command.
 *
 * @param actionCommand The action command to include in the event
 */

   public void fireActionEvent(String actionCommand) {
      fireActionEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                                      actionCommand));
   }

/**
 * Triggers an action event for this graphical object.
 *
 * @param e The <code>ActionEvent</code> to fire
 */

   public void fireActionEvent(ActionEvent e) {
      if (actionListener != null) actionListener.actionPerformed(e);
   }

/**
 * Overrides <code>clone</code> in <code>Object</code> to make sure that
 * the transient fields are set to <code>null</code> in the clone and that
 * the transformation matrix is copied.
 */

   @Override
   public Object clone() {
      try {
         GObject clone = (GObject) super.clone();
         clone.compoundParent = null;
         clone.transientParent = null;
         clone.mouseListener = null;
         clone.mouseMotionListener = null;
         clone.actionListener = null;
         if (clone.ctm != null) {
            clone.ctm = new GTransform(clone.ctm);
         }
         return clone;
      } catch (Exception CloneNotSupportedException) {
         throw new RuntimeException("Impossible exception");
      }
   }

/**
 * Sets the parent of this object, which should be called only by the
 * <code>GContainer</code> in which this is installed.  The
 * serialization behavior of the parent data depends on the parent
 * type.  Because a <code>GCompound</code> is serializable, it
 * needs to be maintained in a nontransient variable; other parent
 * classes are transient, so that these parents are not recorded
 * in the serial form.
 */

   public void setParent(GContainer parent) {
      if (parent instanceof GCompound) {
         compoundParent = (GCompound) parent;
      } else {
         transientParent = parent;
      }
   }

/**
 * All subclasses of <code>GObject</code> must define a <code>paint2d</code>
 * method which allows the object to draw itself on the <code>Graphics2D</code>
 * context passed in as the parameter <code>g</code>.  This context is
 * already transformed to account for translations, rotations, and scaling,
 * and is called only if the object is visible.
 *
 * @param g The graphics context into which the painting is done
 */

   protected abstract void paint2d(Graphics2D g);

/**
 * All <code>GObject</code> subclasses must define a <code>localBounds</code>
 * method that returns the bounding box of the object relative to its origin.
 *
 * @param ctm The transformation matrix
 * @return The bounds for the object relative to its origin
 */

   protected abstract GRectangle localBounds(GTransform ctm);

/**
 * All <code>GObject</code> sublcasses must define a <code>localContains</code>
 * method that checks whether the specified point is contained in the
 * figure.  The <code>contains</code> method in the <code>GObject</code>
 * class has already translated the coordinates to the local coordinate
 * space and then applied the inverse coordinate transformation.  This
 * method must therefore check whether the point is inside the untransformed
 * local bounds of the object.
 *
 * @param x The x coordinate of the point
 * @param y The y coordinate of the point
 */

   protected abstract boolean localContains(double x, double y);

/**
 * Returns <code>true</code> if the object has been transformed.
 *
 * @return The value <code>true</code> if the object has been transformed
 */

   protected boolean isTransformed() {
      return transformed;
   }

/**
 * Sends the event to the appropriate listener.
 *
 * @param e The <code>MouseEvent</code> that triggered this response
 */

   protected void fireMouseListeners(MouseEvent e) {
      switch (e.getID()) {
        case MouseEvent.MOUSE_PRESSED:
         if (mouseListener != null) mouseListener.mousePressed(e);
         break;
        case MouseEvent.MOUSE_RELEASED:
         if (mouseListener != null) mouseListener.mouseReleased(e);
         break;
        case MouseEvent.MOUSE_CLICKED:
         if (mouseListener != null) mouseListener.mouseClicked(e);
         break;
        case MouseEvent.MOUSE_EXITED:
         if (mouseListener != null) mouseListener.mouseExited(e);
         break;
        case MouseEvent.MOUSE_ENTERED:
         if (mouseListener != null) mouseListener.mouseEntered(e);
         break;
        case MouseEvent.MOUSE_MOVED:
         if (mouseMotionListener != null) mouseMotionListener.mouseMoved(e);
         break;
        case MouseEvent.MOUSE_DRAGGED:
         if (mouseMotionListener != null) mouseMotionListener.mouseDragged(e);
         break;
      }
   }

/**
 * Returns <code>true</code> if mouse listeners have ever been assigned to
 * this object.
 *
 * @return <code>true</code> if mouse listeners have been enabled
 */

   protected boolean areMouseListenersEnabled() {
      return mouseListenersEnabled;
   }

/**
 * This method returns the color set for this specific object, which may
 * be <code>null</code>.  It differs from <code>getColor</code> in that
 * it does not walk up the containment chain.
 */

   protected Color getObjectColor() {
      return objectColor;
   }

/**
 * This method returns the transformation matrix.
 */

   protected GTransform getCTM() {
      return ctm;
   }

/**
 * This method returns the inverse of the transformation matrix.
 */

   protected GTransform getITM() {
      if (itm == null) itm = ctm.createInverse();
      return itm;
   }

/**
 * This method returns a new graphics context that includes all the
 * transformations for the object.
 */

   protected Graphics2D createTransformedGraphics(Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();
      Color objectColor = getObjectColor();
      if (objectColor != null) g2d.setColor(objectColor);
      AffineTransform at = new AffineTransform(ctm.getScaleX(),
                                               ctm.getShearY(),
                                               ctm.getShearX(),
                                               ctm.getScaleY(),
                                               ctm.getTranslateX(),
                                               ctm.getTranslateY());
      g2d.translate(getX(), getY());
      g2d.setStroke(new BasicStroke((float) lineWidth));
      g2d.transform(at);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
      return g2d;
   }

/**
 * Returns a string indicating the parameters of this object.
 */

   protected String paramString() {
      String param = "";
      if (this instanceof GResizable) {
         GRectangle r = getBounds();
         param += "bounds=(" + dts(r.getX()) + ", " + dts(r.getY()) + ", "
                  + dts(r.getWidth()) + ", " + dts(r.getHeight()) + ")";
      } else {
         GPoint pt = getLocation();
         param += "location=(" + dts(pt.getX()) + ", " + dts(pt.getY()) + ")";
      }
      if (objectColor != null) {
         param += ", color=" + colorName(objectColor);
      }
      if (this instanceof GFillable) {
         param += ", filled=" + ((GFillable) this).isFilled();
         Color fillColor = ((GFillable) this).getFillColor();
         if (fillColor != null && fillColor != objectColor) {
            param += ", fillColor=" + colorName(fillColor);
         }
      }
      return param;
   }

/**
 * Converts a <code>double</code> to string form, leaving out the
 * decimal point from integral values.
 */

   protected static String dts(double d) {
      if (d == (long) d) {
         return "" + (long) d;
      } else {
         return "" + d;
      }
   }

/**
 * Translates a color to a string representation.
 */

   protected static String colorName(Color color) {
      if (color.equals(Color.BLACK)) return "BLACK";
      if (color.equals(Color.BLUE)) return "BLUE";
      if (color.equals(Color.CYAN)) return "CYAN";
      if (color.equals(Color.DARK_GRAY)) return "DARK_GRAY";
      if (color.equals(Color.GRAY)) return "GRAY";
      if (color.equals(Color.GREEN)) return "GREEN";
      if (color.equals(Color.LIGHT_GRAY)) return "LIGHT_GRAY";
      if (color.equals(Color.MAGENTA)) return "MAGENTA";
      if (color.equals(Color.ORANGE)) return "ORANGE";
      if (color.equals(Color.PINK)) return "PINK";
      if (color.equals(Color.RED)) return "RED";
      if (color.equals(Color.WHITE)) return "WHITE";
      if (color.equals(Color.YELLOW)) return "YELLOW";
      int rgb = color.getRGB() & 0xFFFFFF;
      return "0x" + Integer.toString(rgb, 16).toUpperCase();
   }

/**
 * Returns the component in which this object is installed, or
 * <code>null</code> if none exists.
 *
 * @return The component in which this object is installed,
 *         or <code>null</code> if none exists
 */

   protected Component getComponent() {
      GContainer parent = getParent();
      while (parent instanceof GObject) {
         parent = ((GObject) parent).getParent();
      }
      return (parent instanceof Component) ? (Component) parent : null;
   }

/**
 * Tells the parent to update its list of enabled objects.
 */

   protected void updateEnabledList() {
      Component comp = getComponent();
      if (comp instanceof GCanvas) {
         ((GCanvas) comp).updateEnabledList();
      }
   }

/**
 * Signals that the object needs to be repainted.
 */

   protected void repaint() {
      GContainer parent = getParent();
      while (parent instanceof GObject) {
         parent = ((GObject) parent).getParent();
      }
      if (parent instanceof GCanvas) {
         ((GCanvas) parent).conditionalRepaint();
      }
   }

/* Private instance variables */

   private GCompound compoundParent;
   private GTransform ctm;
   private GTransform itm;
   private Color objectColor;
   private double lineWidth;
   private double x;
   private double y;
   private boolean transformed;
   private boolean isVisible;
   private boolean mouseListenersEnabled;

   private transient MouseListener mouseListener;
   private transient MouseMotionListener mouseMotionListener;
   private transient ActionListener actionListener;
   private transient GContainer transientParent;

}
