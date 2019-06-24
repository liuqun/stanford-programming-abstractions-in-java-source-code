/*
 * File: Move.java
 * ---------------
 * This class represents the superclass for all moves in two-player games.
 */

package edu.stanford.cs.javacs2.ch10;

/**
 * This class represents the common superclass for moves in a two-player
 * game.  At this level, the class exports getters and setters for the
 * rating of the move.  Clients should extend this class to include
 * whatever fields are necessary to define a move in a particular game.
 */

public abstract class Move {

/**
 * Gets the rating for this move, as previously set by setRating.
 *
 * @return The rating for this move
 */

   public int getRating() {
      return rating;
   }

/**
 * Sets the rating for this move.
 *
 * @param rating The rating for this move
 */

   public void setRating(int rating) {
      this.rating = rating;
   }

/* Private instance variables */

   private int rating;

}
