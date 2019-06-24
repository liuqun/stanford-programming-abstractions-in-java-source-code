/*
 * File: PointTest.java
 * --------------------
 * This code tests to see whether the Point class seems to work.
 */

package edu.stanford.cs.javacs2.ch7;

public class PointTest {

   public void run() {
      Point origin = new Point();
      Point pt = new Point(3, 4);
      double dist = Math.sqrt(pt.x * pt.x + pt.y * pt.y);
      System.out.println("origin = " + origin);
      System.out.println("pt = " + pt);
      System.out.println("distance(origin, pt) = " + dist);
   }

/* Main program */

   public static void main(String[] args) {
      new PointTest().run();
   }

}
