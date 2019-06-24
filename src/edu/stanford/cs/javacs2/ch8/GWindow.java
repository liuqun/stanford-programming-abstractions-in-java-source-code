/*
 * File: GWindow.java
 * ------------------
 * This file exports the GWindow class, which is a JFrame containing GObjects.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import javax.swing.JFrame;

public class GWindow extends JFrame {

/* Creates a new GWindow containing a GCanvas with no preferred size */

   public GWindow() {
      this(new GCanvas());
   }

/* Creates a new GWindow containing a GCanvas with a preferred size */

   public GWindow(double width, double height) {
      this(new GCanvas(width, height));
   }

/* Creates a new GWindow containing a GCanvas object */

   public GWindow(GCanvas gc) {
      String title = System.getProperty("sun.java.command");
      setTitle((title == null) ? "Graphics Window" :
                                 title.substring(title.lastIndexOf('.') + 1));
      setBackground(Color.WHITE);
      this.gc = gc;
      add(gc);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

/* Adds the graphical object to this canvas */

   public void add(GObject gobj) {
      gc.add(gobj);
   }

/* Returns the GCanvas embedded in this GWindow */

   public GCanvas getGCanvas() {
      return gc;
   }

/* Private instance variables */

   private GCanvas gc;

}
