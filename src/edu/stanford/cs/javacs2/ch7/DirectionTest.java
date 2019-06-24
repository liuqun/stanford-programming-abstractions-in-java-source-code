/*
 * File: DirectionTest.java
 * ------------------------
 * This program checks the implementation of the Direction type.
 */

package edu.stanford.cs.javacs2.ch7;

public class DirectionTest {

   public void run() {
      for (Direction dir : Direction.values()) {
         System.out.println(dir.turnLeft() +
                            " <- " + dir + " -> " +
                            dir.turnRight());
      }
   }

/* Main program */

   public static void main(String[] args) {
      new DirectionTest().run();
   }

}
