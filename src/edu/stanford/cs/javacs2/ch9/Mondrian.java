/*
 * File: Mondrian.java
 * -------------------
 * This program draws a recursive Mondrian style picture by recursively
 * subdividing the plane.
 */

package edu.stanford.cs.javacs2.ch9;

import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GWindow;

public class Mondrian {

   public void run() {
      GWindow gw = new GWindow(WIDTH, HEIGHT);
      subdivideCanvas(gw, 0, 0, WIDTH, HEIGHT);
   }

/*
 * At each level, subdivideCanvas first checks for the simple case, which
 * is when the size of the rectangular canvas is too small to subdivide
 * (i.e., when the area is less than MINIMUM_AREA).  In the simple case,
 * the method does nothing.  In the recursive case, the method splits the
 * canvas along its longest dimension by choosing a random dividing line
 * that leaves at least MINIMUM_EDGE on each side.  The program then uses
 * a divide-and-conquer strategy to subdivide the two new rectangles.
 */

   private void subdivideCanvas(GWindow gw, double x, double y,
                                            double width, double height) {
      if (width * height >= MINIMUM_AREA) {
         if (width > height) {
            double dx = randomReal(MINIMUM_EDGE, width - MINIMUM_EDGE);
            gw.add(new GLine(x + dx, y, x + dx, y + height));
            subdivideCanvas(gw, x, y, dx, height);
            subdivideCanvas(gw, x + dx, y, width - dx, height);
         } else {
            double dy = randomReal(MINIMUM_EDGE, height - MINIMUM_EDGE);
            gw.add(new GLine(x, y + dy, x + width, y + dy));
            subdivideCanvas(gw, x, y, width, dy);
            subdivideCanvas(gw, x, y + dy, width, height - dy);
         }
      }
   }

/**
 * Returns a random real number in the specified range.
 */

   private double randomReal(double low, double high) {
      return low + Math.random() * (high - low);
   }

/* Private constants */

   private static final int WIDTH = 700;
   private static final int HEIGHT = 400;
   private static final double MINIMUM_AREA = 4000;
   private static final double MINIMUM_EDGE = 10;

/* Main program */

   public static void main(String[] args) {
      new Mondrian().run();
   }

}
