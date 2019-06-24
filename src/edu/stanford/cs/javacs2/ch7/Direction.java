/*
 * File: Direction.java
 * --------------------
 * This file defines an enumerated type called Direction whose values are
 * the four major compass points: NORTH, EAST, SOUTH, and WEST.
 */

package edu.stanford.cs.javacs2.ch7;

/**
 * This enumerated type represents a direction which must be one of the
 * four major compass points (NORTH, EAST, SOUTH, WEST).
 */

public enum Direction {
   NORTH, EAST, SOUTH, WEST;

/**
 * Returns the direction that is 90 degrees to the left of this one.
 *
 * @return The direction 90 degrees to the left
 */

   public Direction turnLeft() {
      switch (this) {
       case NORTH: return WEST;
       case EAST: return NORTH;
       case SOUTH: return EAST;
       case WEST: return SOUTH;
      }
      throw new RuntimeException("Illegal direction");
   }

/**
 * Returns the direction that is 90 degrees to the right of this one.
 *
 * @return The direction 90 degrees to the right
 */

   public Direction turnRight() {
      switch (this) {
       case NORTH: return EAST;
       case EAST: return SOUTH;
       case SOUTH: return WEST;
       case WEST: return NORTH;
      }
      throw new RuntimeException("Illegal direction");
   }

}
