/*
 * File: GProgram.java
 * -------------------
 * This class makes it easy to write graphics programs by creating a
 * GWindow object and forwarding commands to it.
 */

package edu.stanford.cs.graphics;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import javax.swing.JButton;
   
/**
 * The <code>GProgram</code> class creates a <code>GWindow</code> object
 * and displays it on the screen.
 */

public class GProgram
       implements MouseListener, MouseMotionListener,
                  KeyListener, ActionListener, Iterable<GObject> {

/**
 * Creates a <code>GWindow</code> object and installs it on the screen.
 */

   public GProgram() {
      gw = new GWindow();
      gw.setAutoRepaintFlag(false);
      eventListener = new GProgramListener(gw);
      gw.addComponentListener(eventListener);
      gw.getGCanvas().addMouseListener(eventListener);
   }

/**
 * Sets the size of the graphical canvas.
 *
 * @param width The width of the embedded <code>GCanvas</code>
 * @param height The height of the embedded <code>GCanvas</code>
 */

   public void setSize(double width, double height) {
      Dimension size = new Dimension(GMath.round(width), GMath.round(height));
      GCanvas gc = gw.getGCanvas();
      gc.setSize(size);
      gc.setPreferredSize(size);
      gw.pack();
   }

/**
 * Returns the embedded <code>GWindow</code> object.
 *
 * @return The embedded <code>GWindow</code>
 */

   public GWindow getGWindow() {
      return gw;
   }

/**
 * Returns the embedded <code>GCanvas</code> object.
 *
 * @return The embedded <code>GCanvas</code>
 */

   public GCanvas getGCanvas() {
      return gw.getGCanvas();
   }

/**
 * Returns the width of the graphics canvas in pixels.
 *
 * @return The width of the graphics canvas
 */

   public double getWidth() {
      return gw.getCanvasWidth();
   }

/**
 * Returns the height of the graphics canvas in pixels.
 *
 * @return The height of the graphics canvas
 */

   public double getHeight() {
      return gw.getCanvasHeight();
   }

/**
 * Adds the graphical object to this canvas.
 *
 * @param gobj The graphical object to add
 */

   public void add(GObject gobj) {
      gw.add(gobj);
   }

/**
 * Adds the graphical object to this canvas and sets its location
 * to the point (<code>x</code>,&nbsp;<code>y</code>).
 *
 * @param gobj The graphical object to add
 * @param x The new x-coordinate for the object
 * @param y The new y-coordinate for the object
 */

   public void add(GObject gobj, double x, double y) {
      gw.add(gobj, x, y);
   }

/**
 * Adds the graphical object to this canvas and sets its location to
 * the specified point.
 *
 * @param gobj The graphical object to add
 * @param pt A <code>GPoint</code> object giving the coordinates of the point
 */

   public void add(GObject gobj, GPoint pt) {
      gw.add(gobj, pt);
   }

/**
 * Removes a graphical object from this <code>GWindow</code>.
 *
 * @param gobj The graphical object to remove
 */

   public void remove(GObject gobj) {
      gw.remove(gobj);
   }

/**
 * Removes all graphical objects from the window.  This method is a synonym
 * for <code>removeAll</code>.
 */

   public void clear() {
      removeAll();
   }

/**
 * Removes all graphical objects from the window.
 */

   public void removeAll() {
      gw.removeAll();
   }

/**
 * Returns the number of graphical objects stored in this <code>GWindow</code>.
 *
 * @return The number of graphical objects in this <code>GWindow</code>
 */

   public int getElementCount() {
      return gw.getElementCount();
   }

/**
 * Returns the graphical object at the specified index, numbering from back
 * to front in the the <i>z</i> dimension.
 *
 * @param index The index of the component to return
 * @return The graphical object at the specified index
 */

   public GObject getElement(int index) {
      return gw.getElement(index);
   }

/**
 * Returns the topmost graphical object that contains the point
 * (<code>x</code>, <code>y</code>), or <code>null</code> if no such
 * object exists.
 *
 * @param x The x-coordinate of the point being tested
 * @param y The y-coordinate of the point being tested
 * @return The graphical object at the specified location, or
 *         <code>null</code> if no such object exists
 */

   public GObject getElementAt(double x, double y) {
      return gw.getElementAt(x, y);
   }

/**
 * Returns the topmost graphical object that contains the specified point,
 * or <code>null</code> if no such object exists.
 *
 * @param pt The coordinates being tested
 * @return The graphical object at the specified location, or
 *         <code>null</code> if no such object exists
 */

   public GObject getElementAt(GPoint pt) {
      return gw.getElementAt(pt);
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
      return gw.iterator();
   }

/**
 * Returns an <code>Iterator</code> that cycles through the elements within
 * this container in the specified direction, which must be one of the
 * constants <a href="GContainer.html#FRONT_TO_BACK"
 * ><code>GContainer.FRONT_TO_BACK</code></a> or
 * <a href="GContainer.html#BACK_TO_FRONT"
 * ><code>GContainer.BACK_TO_FRONT</code></a>.<p>
 *
 * @return An <code>Iterator</code> ranging over the elements of the
 *         container in the specified direction
 */

   public Iterator<GObject> iterator(int direction) {
      return gw.iterator(direction);
   }

/**
 * Issues a repaint request for the graphics window.
 */

   public void repaint() {
      gw.repaint();
   }

/**
 * Waits for a mouse click in the window before proceeding.
 */

   public void waitForClick() {
      repaint();
      eventListener.waitForClick();
   }

/**
 * Delays the calling thread for the specified time, which is expressed in
 * milliseconds.  Unlike <code>Thread.sleep</code>, this method never throws
 * an exception.
 *
 * @param milliseconds The sleep time in milliseconds
 */

   public void pause(double milliseconds) {
      repaint();
      try {
         int millis = (int) milliseconds;
         int nanos = (int) Math.round((milliseconds - millis) * 1000000);
         Thread.sleep(millis, nanos);
      } catch (InterruptedException ex) {
         /* Empty */
      }
   }

/**
 * Adds the program as an <code>ActionListener</code> to every button in
 * the structure that does not have a listener already.
 */

   public void addActionListeners() {
      addActionListeners(this);
   }

/**
 * Adds the specified listener to every button in the structure that does
 * not have a listener already.
 *
 * @param listener The <code>ActionListener</code> to be added
 */

   public void addActionListeners(ActionListener listener) {
      addActionListeners(gw, listener);
   }

/**
 * Adds the program as both a <code>MouseListener</code> and
 * <code>MouseMotionListener</code> to the canvas.
 */

   public void addMouseListeners() {
      GCanvas gc = gw.getGCanvas();
      gc.addMouseListener(this);
      gc.addMouseMotionListener(this);
   }

/**
 * Adds the program as a <code>KeyListener</code> to the canvas.
 */

   public void addKeyListeners() {
      gw.getGCanvas().addKeyListener(this);
   }

/* Listener methods */

/**
 * Called when the mouse is clicked.  A call to <code>mouseClicked</code>
 * is always preceded by both a <code>mousePressed</code> and a
 * <code>mouseReleased</code> event for the same source.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseClicked(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse button is pressed.
 *
 * @param e The mouse event
 */

   @Override
   public void mousePressed(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse button is released.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseReleased(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse enters the source.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseEntered(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse exits the source.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseExited(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse is moved.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseMoved(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when the mouse is dragged with the button down.  Java makes
 * several guarantees about dragging.  First, a <code>mouseDragged</code>
 * call is always preceded by a <code>mousePressed</code> call for the
 * same source.  If the mouse is pressed elsewhere and then enters a
 * source with the button down, no drag event occurs.  Moreover, once
 * the mouse button goes down in a particular source, only that source
 * will receive mouse events until the button goes up.  Those events,
 * moreover, are reported even in the mouse travels outside the domain
 * of the object.
 *
 * @param e The mouse event
 */

   @Override
   public void mouseDragged(MouseEvent e) {
      /* Empty */
   }

/**
 * Called when a key is typed (i.e., pressed and released).
 *
 * @param e The key event
 */

   @Override
   public void keyTyped(KeyEvent e) {
      /* Empty */
   }

/**
 * Called when a key is pressed.
 *
 * @param e The key event
 */

   @Override
   public void keyPressed(KeyEvent e) {
      /* Empty */
   }

/**
 * Called when a key is released.
 *
 * @param e The key event
 */

   @Override
   public void keyReleased(KeyEvent e) {
      /* Empty */
   }

/**
 * Called when a component (typically a button) is activated.
 *
 * @param e The action event
 */

   @Override
   public void actionPerformed(ActionEvent e) {
      /* Empty */
   }

/* Private methods */

/**
 * Recursively adds the specified listener as an <code>ActionListener</code>
 * to every <code>JButton</code> in the hierarchy.
 */

   private void addActionListeners(Component comp, ActionListener listener) {
      if (comp instanceof JButton) {
         JButton button = (JButton) comp;
         if (button.getActionListeners().length == 0) {
            button.addActionListener(listener);
         }
      } else if (comp instanceof Container) {
         Container container = (Container) comp;
         int nComponents = container.getComponentCount();
         for (int i = 0; i < nComponents; i++) {
            addActionListeners(container.getComponent(i), listener);
         }
      }
   }

/* Private instance variables */

   private GProgramListener eventListener;
   private GWindow gw;

}

class GProgramListener implements ComponentListener, MouseListener, Runnable {

   public GProgramListener(GWindow gw) {
      this.gw = gw;
      mainThread = Thread.currentThread();
      new Thread(this).start();
   }

/**
 * Waits for a mouse click in the window before proceeding.
 */

   public synchronized void waitForClick() {
      clickFlag = false;
      while (!clickFlag) {
         try {
            wait();
         } catch (InterruptedException ex) {
            /* Empty */
         }
      }
   }

/* Runnable interface */

   @Override
   public void run() {
      try {
         mainThread.join();
      } catch (InterruptedException ex) {
         /* Ignore */
      }
      gw.repaint();
   }

/* ComponentListener interface */

   @Override
   public void componentHidden(ComponentEvent e) {
      /* Empty */
   }

   @Override
   public void componentMoved(ComponentEvent e) {
      /* Empty */
   }

   @Override
   public void componentResized(ComponentEvent e) {
      gw.repaint();
   }

   @Override
   public void componentShown(ComponentEvent e) {
      gw.repaint();
   }

/* MouseListener interface */

   @Override
   public void mouseClicked(MouseEvent e) {
      signalClickOccurred();
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      /* Empty */
   }

   @Override
   public void mouseExited(MouseEvent e) {
      /* Empty */
   }

   @Override
   public void mousePressed(MouseEvent e) {
      /* Empty */
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      /* Empty */
   }

/* Private methods */

/**
 * Notifies any waiting objects that a click has occurred.
 */

   private synchronized void signalClickOccurred() {
      clickFlag = true;
      notifyAll();
   }

/* Private instance variables */

   private GWindow gw;
   private Thread mainThread;
   private boolean clickFlag;

}
