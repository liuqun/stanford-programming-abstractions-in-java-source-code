/*
 * File: DrawLines.java
 * --------------------
 * This program allows users to draw lines on the graphics window by
 * clicking and dragging with the mouse.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.javacs2.ch8.GCanvas;
import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawLines extends MouseAdapter {

   public void run() {
      gw = new GWindow(WIDTH, HEIGHT);
      GCanvas gc = gw.getGCanvas();
      gc.addMouseListener(this);
      gc.addMouseMotionListener(this);
   }

/* Called on mouse press to create a new line */

   @Override
   public void mousePressed(MouseEvent e) {
      line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
      gw.add(line);
   }

/* Called on mouse drag to reset the endpoint */

   @Override
   public void mouseDragged(MouseEvent e) {
      line.setEndPoint(e.getX(), e.getY());
   }

/* Constants */

   private static final int WIDTH = 500;
   private static final int HEIGHT = 300;

/* Private instance variables */

   private GWindow gw;
   private GLine line;

/* Main program */

   public static void main(String[] args) {
      new DrawLines().run();
   }

}
