/*
 * File: GCanvas.java
 * ------------------
 * This file exports the GCanvas class, which is a graphical component
 * capable of containing GObjects.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

public class GCanvas extends JComponent {

/* Creates a new GCanvas with no preferred size */

   public GCanvas() {
      contents = new ArrayList<GObject>();
   }

/* Creates a new GCanvas with the specified preferred size */

   public GCanvas(double width, double height) {
      this();
      setSize((int) Math.round(width), (int) Math.round(height));
      setPreferredSize(getSize());
   }

/* Adds the graphical object to this canvas */

   public void add(GObject gobj) {
      synchronized (contents) {
         contents.add(gobj);
         gobj.gc = this;
      }
      repaint();
   }

/* Paints the contents of the GCanvas */

   @Override
   public void paintComponent(Graphics g) {
      synchronized (contents) {
         for (GObject gobj : contents) {
            gobj.paint(g);
         }
      }
   }

/* Private instance variables */

   private ArrayList<GObject> contents;

}
