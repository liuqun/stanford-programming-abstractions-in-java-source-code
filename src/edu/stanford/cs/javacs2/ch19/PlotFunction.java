/*
 * File: PlotFunction.java
 * -----------------------
 * This program plots the Math.sin function on the graphics window.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GWindow;

public class PlotFunction {

   public void run() {
      GWindow gw = new GWindow(WIDTH, HEIGHT);
      plot(gw, new Function<Double,Double>() {
                 public Double apply(Double x) {
                    return Math.sin(x);
                 }
               }, -2 * Math.PI, 2 * Math.PI, -1, 1);
   }

/**
 * Plots the specified function on the graphics window.
 *
 * @param gw The graphics window
 * @param fn A lambda expression that implements Function<Double,Double>
 * @param minX The minimum x value at the left edge of the window
 * @param maxX The maximum x value at the right edge of the window
 * @param minY The minimum x value at the bottom of the window
 * @param maxY The maximum x value at the top of the window
 */

   private void plot(GWindow gw, Function<Double,Double> fn,
                     double minX, double maxX,
                     double minY, double maxY) {
      double dx = (maxX - minX) / WIDTH;
      double sx0 = 0;
      double sy0 = HEIGHT - (fn.apply(minX) - minY) / (maxY - minY) * HEIGHT;
      for (int i = 1; i < WIDTH; i++) {
         double x = minX + i * dx;
         double y = fn.apply(x);
         double sx1 = (x - minX) / (maxX - minX) * WIDTH;
         double sy1 = HEIGHT - (y - minY) / (maxY - minY) * HEIGHT;
         gw.add(new GLine(sx0, sy0, sx1, sy1));
         sx0 = sx1;
         sy0 = sy1;
      }
   }

/* Constants */

   private static final int WIDTH = 600;
   private static final int HEIGHT = 400;

/* Main program */

   public static void main(String[] args) {
      new PlotFunction().run();
   }

}
