/*
 * File: DirectionUnitTest.java
 * ----------------------------
 * This program implements a unit test of the enumerated Direction type.
 */

package edu.stanford.cs.javacs2.ch7;

import static edu.stanford.cs.javacs2.ch7.Direction.EAST;
import static edu.stanford.cs.javacs2.ch7.Direction.NORTH;
import static edu.stanford.cs.javacs2.ch7.Direction.SOUTH;
import static edu.stanford.cs.javacs2.ch7.Direction.WEST;
import static edu.stanford.cs.unittest.UnitTest.assertEquals;

public class DirectionUnitTest {

   public void run() {
      assertEquals("EAST.turnLeft() -> %s", EAST.turnLeft(), NORTH);
      assertEquals("NORTH.turnLeft() -> %s", NORTH.turnLeft(), WEST);
      assertEquals("WEST.turnLeft() -> %s", WEST.turnLeft(), SOUTH);
      assertEquals("SOUTH.turnLeft() -> %s", SOUTH.turnLeft(), EAST);
      assertEquals("EAST.turnRight() -> %s", EAST.turnRight(), SOUTH);
      assertEquals("NORTH.turnRight() -> %s", NORTH.turnRight(), EAST);
      assertEquals("WEST.turnRight() -> %s", WEST.turnRight(), NORTH);
      assertEquals("SOUTH.turnRight() -> %s", SOUTH.turnRight(), WEST);
      assertEquals("EAST.toString() -> %s", EAST.toString(), "EAST");
      assertEquals("NORTH.toString() -> %s", NORTH.toString(), "NORTH");
      assertEquals("WEST.toString() -> %s", WEST.toString(), "WEST");
      assertEquals("SOUTH.toString() -> %s", SOUTH.toString(), "SOUTH");
   }

/* Main program */

   public static void main(String[] args) {
      new DirectionUnitTest().run();
   }

}
