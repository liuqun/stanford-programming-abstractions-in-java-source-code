/*
 * File: GCompound.java
 * --------------------
 * This file exports a GObject subclass that contains other GObjects.
 */

package edu.stanford.cs.graphics;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**
 * This class defines a graphical object that consists of a collection
 * of other graphical objects.  Once assembled, the internal objects
 * can be manipulated as a unit.
 */

public class GCompound extends GObject
       implements GContainer, Iterable<GObject> {

/**
 * Creates a new <code>GCompound</code> object with no internal components.
 */

   public GCompound() {
      contents = new GObjectList(this);
   }

/**
 * Adds a new graphical object to this <code>GCompound</code>.
 *
 * @param gobj The graphical object to add
 */

   @Override
   public void add(GObject gobj) {
      contents.add(gobj);
      repaint();
   }

/**
 * Adds the graphical object to this canvas and sets its location
 * to the point (<code>x</code>,&nbsp;<code>y</code>).
 *
 * @param gobj The graphical object to add
 * @param x The new x-coordinate for the object
 * @param y The new y-coordinate for the object
 */

   @Override
   public final void add(GObject gobj, double x, double y) {
      gobj.setLocation(x, y);
      add(gobj);
   }

/**
 * Adds the graphical object to this canvas and sets its location to the
 * specified point.
 *
 * @param gobj The graphical object to add
 * @param pt A <code>GPoint</code> object giving the coordinates of the point
 */

   @Override
   public final void add(GObject gobj, GPoint pt) {
      add(gobj, pt.getX(), pt.getY());
   }

/**
 * Removes a graphical object from this <code>GCompound</code>.
 *
 * @param gobj The graphical object to remove
 */

   @Override
   public void remove(GObject gobj) {
      contents.remove(gobj);
      repaint();
   }

/**
 * Removes all graphical objects from this <code>GCompound</code>.
 */

   @Override
   public void removeAll() {
      contents.removeAll();
      repaint();
   }

/**
 * Returns the number of graphical objects stored in this container.
 *
 * @return The number of graphical objects in this container
 */

   @Override
   public int getElementCount() {
      return contents.getElementCount();
   }

/**
 * Returns the graphical object at the specified index, numbering from back
 * to front in the the <i>z</i> dimension.
 *
 * @param index The index of the component to return
 * @return The graphical object at the specified index
 */

   @Override
   public GObject getElement(int index) {
      return contents.getElement(index);
   }

/**
 * Returns the topmost graphical object that contains the point
 * (<code>x</code>, <code>y</code>), or <code>null</code> if no such
 * object exists.  Note that these coordinates are relative to the
 * location of the compound object and not to the canvas in which
 * it is displayed.
 *
 * @param x The x-coordinate of the point being tested
 * @param y The y-coordinate of the point being tested
 * @return The graphical object at the specified location, or
 *         <code>null</code> if no such object exists
 */

   @Override
   public GObject getElementAt(double x, double y) {
      return contents.getElementAt(x, y, false);
   }

/**
 * Returns the topmost graphical object that contains the specified point,
 * or <code>null</code> if no such object exists.
 *
 * @param pt The coordinates being tested
 * @return The graphical object at the specified location, or
 *         <code>null</code> if no such object exists
 */

   @Override
   public final GObject getElementAt(GPoint pt) {
      return getElementAt(pt.getX(), pt.getY());
   }

/**
 * Returns an <code>Iterator</code> that cycles through the elements within
 * this container in the default direction, which is from back to front.
 * You can also run the iterator in the opposite direction by using the
 * <a href="#iterator(int)"><code>iterator</code></a><code>(</code><font
 * size=-1><i>direction</i></font><code>)</code> form of this method.
 *
 * @return An <code>Iterator</code> ranging over the elements of the
 *         container from back to front
 */

   @Override
   public Iterator<GObject> iterator() {
      return new GIterator(this, GContainer.BACK_TO_FRONT);
   }

/**
 * Returns an <code>Iterator</code> that cycles through the elements
 * within this container in the specified direction, which must be one
 * of the constants <a href="GContainer.html#FRONT_TO_BACK"
 * ><code>GContainer.FRONT_TO_BACK</code></a> or
 * <a href="GContainer.html#BACK_TO_FRONT"
 * ><code>GContainer.BACK_TO_FRONT</code></a>.<p>
 *
 * @return An <code>Iterator</code> ranging over the elements of the
 *         container in the specified direction
 */

   public Iterator<GObject> iterator(int direction) {
      return new GIterator(this, direction);
   }

/**
 * Overrides <code>clone</code> in <code>GObject</code> to make sure that
 * the contents vector is copied.
 */

   @Override
   public Object clone() {
      try {
         GCompound clone = (GCompound) super.clone();
         clone.contents = new GObjectList(clone, contents);
         return clone;
      } catch (Exception CloneNotSupportedException) {
         throw new RuntimeException("Impossible exception");
      }
   }

/**
 * Converts the location of the specified point in this compound to
 * the corresponding point in the enclosing canvas.
 *
 * @param localPoint The coordinates in the space of the compound
 * @return The coordinates in the space of the enclosing <code>GCanvas</code>
 */

   public final GPoint getCanvasPoint(GPoint localPoint) {
      return getCanvasPoint(localPoint.getX(), localPoint.getY());
   }

/**
 * Converts the location of the specified point in this compound to
 * the corresponding point in the enclosing canvas.
 *
 * @param x The x coordinate in the space of the compound
 * @param y The y coordinate in the space of the compound
 * @return The coordinates in the space of the enclosing <code>GCanvas</code>
 */

   public GPoint getCanvasPoint(double x, double y) {
      // Add transformation code
      for (GContainer c = this; c instanceof GCompound; ) {
         GCompound comp = (GCompound) c;
         x += comp.getX();
         y += comp.getY();
         c = comp.getParent();
      }
      return new GPoint(x, y);
   }

/**
 * Converts the location of the specified point on the enclosing canvas
 * to the corresponding point in the space of this compound.
 *
 * @param canvasPoint The coordinates in the space of the enclosing
 *                    <code>GCanvas</code>
 * @return The coordinates in the space of the compound
 */

   public final GPoint getLocalPoint(GPoint canvasPoint) {
      return getLocalPoint(canvasPoint.getX(), canvasPoint.getY());
   }

/**
 * Converts the specified point on the enclosing canvas to the
 * corresponding point in the space of this compound.
 *
 * @param x The x coordinate in the space of the space of the enclosing
 *          <code>GCanvas</code>
 * @param y The y coordinate in the space of the space of the enclosing
 *          <code>GCanvas</code>
 * @return The coordinates in the space of the compound
 */

   public GPoint getLocalPoint(double x, double y) {
      // Add transformation code
      for (GContainer c = this; c instanceof GCompound; ) {
         GCompound comp = (GCompound) c;
         x -= comp.getX();
         y -= comp.getY();
         c = comp.getParent();
      }
      return new GPoint(x, y);
   }

/* Protected methods */

/**
 * Returns the bounding box of the object relative to its origin.
 */

   @Override
   protected GRectangle localBounds(GTransform ctm) {
      GRectangle bb = new GRectangle();
      synchronized (contents) {
         int n = contents.getElementCount();
         for (int i = 0; i < n; i++) {
            GObject obj = contents.getElement(i);
            GTransform t = new GTransform(ctm);
            t.translate(obj.getX(), obj.getY());
            t.concatenate(obj.getCTM());           
            GRectangle r = obj.localBounds(t);
            if (i == 0) {
               bb = r;
            } else {
               bb.add(r);
            }
         }
      }
      return bb;
   }

/**
 * Checks to see whether a point is "inside" the compound, which means
 * that it is inside one of the components.
 *
 * @param x The x-coordinate of the point being tested
 * @param y The y-coordinate of the point being tested
 * @return <code>true</code> if the point (<code>x</code>,&nbsp;<code>y</code>)
 *         is inside the compound, and <code>false</code> otherwise
 */

   @Override
   protected boolean localContains(double x, double y) {
      synchronized (contents) {
         int n = contents.getElementCount();
         for (int i = 0; i < n; i++) {
            if (contents.getElement(i).contains(x, y)) return true;
         }
      }      
      return false;
   }

/**
 * Implements the <code>paint2d</code> operation for this graphical object.
 * This method is not called directly by clients.
 */

   @Override
   protected void paint2d(Graphics2D g) {
      contents.mapPaint(g);
   }

/**
 * Implements the <code>sendToFront</code> function from the
 * <code>GContainer</code> interface.  Clients should not call this
 * method, but the semantics of interfaces forces it to be exported.
 */

   protected void sendToFront(GObject gobj) {
      contents.sendToFront(gobj);
      repaint();
   }

/**
 * Implements the <code>sendToBack</code> function from the
 * <code>GContainer</code> interface.  Clients should not call this
 * method, but the semantics of interfaces forces it to be exported.
 */

   protected void sendToBack(GObject gobj) {
      contents.sendToBack(gobj);
      repaint();
   }

/**
 * Implements the <code>sendForward</code> function from the
 * <code>GContainer</code> interface.  Clients should not call this
 * method, but the semantics of interfaces forces it to be exported.
 */

   protected void sendForward(GObject gobj) {
      contents.sendForward(gobj);
      repaint();
   }

/**
 * Implements the <code>sendBackward</code> function from the
 * <code>GContainer</code> interface.  Clients should not call this
 * method, but the semantics of interfaces forces it to be exported.
 */

   protected void sendBackward(GObject gobj) {
      contents.sendBackward(gobj);
      repaint();
   }

/**
 * Dispatches a mouse event to the topmost child that covers the location
 * in the event <code>e</code>.
 */

   @Override
   protected void fireMouseListeners(MouseEvent e) {
      if (super.areMouseListenersEnabled()) {
         super.fireMouseListeners(e);
         return;
      }
      GPoint pt = new GPoint(e.getX() - getX(), e.getY() - getY());
      GObject gobj = getElementAt(pt);
      MouseEvent newEvent = null;
      if (gobj != lastObject) {
         if (lastObject != null) {
            newEvent = GCanvas.createMouseEvent(lastObject,
                                                MouseEvent.MOUSE_EXITED, e);
            lastObject.fireMouseListeners(newEvent);
         }
         if (gobj != null) {
            newEvent = GCanvas.createMouseEvent(gobj,
                                                MouseEvent.MOUSE_ENTERED, e);
            gobj.fireMouseListeners(newEvent);
         }
      }
      lastObject = gobj;
      if (dragObject != null) gobj = dragObject;
      if (gobj != null) {
         int id = e.getID();
         if (id != MouseEvent.MOUSE_EXITED && id != MouseEvent.MOUSE_ENTERED) {
            if (id == MouseEvent.MOUSE_PRESSED) {
               dragObject = gobj;
            } else if (id == MouseEvent.MOUSE_RELEASED) {
               dragObject = null;
            }
            newEvent = GCanvas.createMouseEvent(gobj, id, e);
            gobj.fireMouseListeners(newEvent);
         }
      }
      if (newEvent != null && newEvent.isConsumed()) e.consume();
   }

/**
 * Returns <code>true</code> if mouse listeners have ever been assigned to
 * this object or to any of the contained objects.
 *
 * @return <code>true</code> if mouse listeners have been enabled in
 *         this object
 */

   @Override
   protected boolean areMouseListenersEnabled() {
      if (super.areMouseListenersEnabled()) return true;
      return contents.areMouseListenersEnabled();
   }

/* Private instance variables */

   private GObjectList contents;
   private transient GObject lastObject;
   private transient GObject dragObject;

}
