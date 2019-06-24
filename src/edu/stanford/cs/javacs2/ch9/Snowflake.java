/*
 * File: Snowflake.java
 * --------------------
 * This program draws a recursive fractal snowflake using GLine segments.
 */

package edu.stanford.cs.javacs2.ch9;

import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GWindow;

public class Snowflake {

   public void run() {
      gw = new GWindow(WIDTH, HEIGHT);
      cx = WIDTH / 2 - EDGE / 2;
      cy = HEIGHT / 2 - EDGE / (2 * Math.sqrt(3));
      addFractalLine(EDGE, 0, ORDER);
      addFractalLine(EDGE, -120, ORDER);
      addFractalLine(EDGE, +120, ORDER);
   }

/*
 * Adds a fractal line to the GCanvas with the specified radial length,
 * starting angle, and fractal order.
 */

   private void addFractalLine(double r, double theta, int order) {
      if (order == 0) {
         addPolarLine(r, theta);
      } else {
         addFractalLine(r / 3, theta, order - 1);
         addFractalLine(r / 3, theta + 60, order - 1);
         addFractalLine(r / 3, theta - 60, order - 1);
         addFractalLine(r / 3, theta, order - 1);
      }
   }

/*
 * Adds a line segment to the GCanvas with the specified radial length
 * and starting angle.
 */

   private void addPolarLine(double r, double theta) {
      double dx = r * Math.cos(Math.toRadians(theta));
      double dy = -r * Math.sin(Math.toRadians(theta));
      gw.add(new GLine(cx, cy, cx + dx, cy + dy));
      cx += dx;
      cy += dy;
   }

/* Constants */

   private static final int WIDTH = 400;
   private static final int HEIGHT = 400;
   private static final int EDGE = 300;
   private static final int ORDER = 4;

/* Private instance variables */

   private GWindow gw;           /* The graphics window      */
   private double cx;            /* The current x coordinate */
   private double cy;            /* The current y coordinate */

/* Main program */

   public static void main(String[] args) {
      new Snowflake().run();
   }

}
