/*
 * File: DrawDotsUsingAnonymousListeners.java
 * ------------------------------------------
 * This program draws a dot everwhere the user clicks the mouse.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.javacs2.ch8.GOval;
import edu.stanford.cs.javacs2.ch8.GWindow;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawDotsUsingAnonymousListeners {

/*
 * This version of the run method uses an anonymous inner class to
 * specify the response to the mouseClicked event.
 */

   public void run() {
      gw = new GWindow(WIDTH, HEIGHT);
      gw.getGCanvas().addMouseListener(
         new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               double r = DOT_RADIUS;
               GOval dot = new GOval(e.getX() - r, e.getY() - r,
                                     2 * r, 2 * r);
               dot.setFilled(true);
               gw.add(dot);
            }
         }
      );
   }

/* Constants */

   private static final double WIDTH = 500;
   private static final double HEIGHT = 300;
   private static final double DOT_RADIUS = 4;

/* Private instance variables */

   private GWindow gw;

/* Main program */

   public static void main(String[] args) {
      new DrawDotsUsingAnonymousListeners().run();
   }

}
