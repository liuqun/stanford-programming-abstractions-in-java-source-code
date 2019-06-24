/*
 * File: GObjectExample.java
 * -------------------------
 * This program illustrates the use of each of the GObject classes.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;

public class GObjectExample {

   public void run() {
      GWindow gw = new GWindow(WIDTH, HEIGHT);
      addDiamond(gw);
      addRectangleAndOval(gw);
   }

/* Adds a diamond connecting the midpoints of the window edges */

   private void addDiamond(GWindow gw) {
      gw.add(new GLine(0, HEIGHT / 2, WIDTH / 2, 0));
      gw.add(new GLine(WIDTH / 2, 0, WIDTH, HEIGHT / 2));
      gw.add(new GLine(WIDTH, HEIGHT / 2, WIDTH / 2, HEIGHT));
      gw.add(new GLine(WIDTH / 2, HEIGHT, 0, HEIGHT / 2));
   }

/* Adds a blue rectangle and a gray oval inscribed in the diamond */

   private void addRectangleAndOval(GWindow gw) {
      GRect rect = new GRect(WIDTH / 4, HEIGHT / 4, WIDTH / 2, HEIGHT / 2);
      rect.setFilled(true);
      rect.setColor(Color.BLUE);
      gw.add(rect);
      GOval oval = new GOval(WIDTH / 4, HEIGHT / 4, WIDTH / 2, HEIGHT / 2);
      oval.setFilled(true);
      oval.setColor(Color.GRAY);
      gw.add(oval);
   }

/* Constants */

   private static final double WIDTH = 500;
   private static final double HEIGHT = 300;

/* Main program */

   public static void main(String[] args) {
      new GObjectExample().run();
   }

}
