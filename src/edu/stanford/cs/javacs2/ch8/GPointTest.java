/*
 * File: GPointTest.java
 * ---------------------
 * This code tests to see whether the GPoint class seems to work.
 */

package edu.stanford.cs.javacs2.ch8;

public class GPointTest {

   public void run() {
      GPoint origin = new GPoint();
      GPoint pt = new GPoint(0.5, 1.2);
      double dist = Math.sqrt(pt.getX() * pt.getX() + pt.getY() * pt.getY());
      System.out.println("origin = " + origin);
      System.out.println("pt = " + pt);
      System.out.println("distance(origin, pt) = " + dist);
   }

/* Main program */

   public static void main(String[] args) {
      new GPointTest().run();
   }

}
