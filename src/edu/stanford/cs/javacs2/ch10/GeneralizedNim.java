/*
 * File: GeneralizedNim.java
 * -------------------------
 * This program simulates a simple variant of the game of Nim.  In this
 * version, the game starts with a pile of 13 coins on a table.  Players
 * then take turns removing 1, 2, or 3 coins from the pile.  The player
 * who takes the last coin loses.  This version of the program generalizes
 * the definition of the run method so that it no longer depends on the
 * characteristics of a particular game.
 */

package edu.stanford.cs.javacs2.ch10;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class GeneralizedNim {

   public void run() {
      initGame();
      printInstructions();
      while (!gameIsOver()) {
         displayGame();
         if (currentPlayer == Player.HUMAN) {
            makeMove(getUserMove());
         } else {
            Move move = getComputerMove();
            displayMove(move);
            makeMove(move);
         }
         switchPlayer();
      }
      announceResult();
   }

/**
 * Initializes the game.
 */

   private void initGame() {
      console = new SystemConsole();
      nCoins = STARTING_COINS;
      currentPlayer = STARTING_PLAYER;
   }

/**
 * Displays the state of the game.
 */

   private void displayGame() {
      System.out.println("There are " + nCoins + " in the pile.");
   }

/**
 * Displays the computer's move.
 */

   private void displayMove(Move move) {
      System.out.println("I'll take " + move.nTaken + ".");
   }

/**
 * Asks the user to enter a move and returns the number of coins taken.
 * If the move is not legal, the user is asked to reenter a valid move.
 */

   private Move getUserMove() {
      int limit = (nCoins < MAX_MOVE) ? nCoins : MAX_MOVE;
      while (true) {
         int nTaken = console.nextInt("How many would you like? ");
         if (nTaken > 0 && nTaken <= limit) {
            Move move = new Move();
            move.nTaken = nTaken;
            return move;
         }
         System.out.println("That's cheating!  Please choose a number" +
                            " between 1 and " + limit);
         displayGame();
      }
   }

/**
 * Figures out what move is best for the computer player and returns
 * the number of coins taken.  The method first calls findGoodMove
 * to see if a winning move exists.  If none does, the program takes
 * only one coin to give the human player more chances to make a mistake.
 */

   private Move getComputerMove() {
      Move move = new Move();
      int nTaken = findGoodMove(nCoins);
      move.nTaken = (nTaken == NO_GOOD_MOVE) ? 1 : nTaken;
      return move;
   }

/**
 * Makes a move in the current game state.
 */

   public void makeMove(Move move) {
      nCoins -= move.nTaken;
   }

/**
 * Returns true if the game is over.
 */

   private boolean gameIsOver() {
      return nCoins <= 1;
   }

/**
 * Looks for a winning move, given the specified number of coins.  If
 * there is a winning move, the method returns that value; if not, the
 * method returns the constant NO_GOOD_MOVE.  The recursive insight is
 * that a good move is one that leaves your opponent in a bad position
 * and a bad position is one that offers no good moves.
 */

   private int findGoodMove(int nCoins) {
      int limit = (nCoins < MAX_MOVE) ? nCoins : MAX_MOVE;
      for (int nTaken = 1; nTaken <= limit; nTaken++) {
         if (isBadPosition(nCoins - nTaken)) return nTaken;
      }
      return NO_GOOD_MOVE;
   }

/**
 * Returns true if nCoins represents a bad position.  Since being left
 * with a single coin is clearly a bad position, having nCoins be equal
 * to 1 represents the simple case of the recursion.
 */

   private boolean isBadPosition(int nCoins) {
      if (nCoins == 1) return true;
      return findGoodMove(nCoins) == NO_GOOD_MOVE;
   }

/**
 * Switches between the human and computer player.
 */

   private void switchPlayer() {
      currentPlayer = (currentPlayer == Player.HUMAN) ? Player.COMPUTER
                                                      : Player.HUMAN;
   }

/**
 * Explains the rules of the game to the user.
 */

   private void printInstructions() {
      System.out.println("Welcome to the game of Nim!");
      System.out.println("In this game, we will start with a pile of " +
                         STARTING_COINS + " coins on the table.");
      System.out.println("On each turn, you and I will alternately take " +
                         "between 1 and " + MAX_MOVE + " coins");
      System.out.println("from the table.  The player who takes the " +
                         "last coin loses.");
      System.out.println();
   }

/**
 * Announces the final result of the game.
 */

   private void announceResult() {
      if (nCoins == 0) {
         System.out.println("You took the last coin.  You lose.");
      } else {
         System.out.println("There is only one coin left.");
         if (currentPlayer == Player.HUMAN) {
            System.out.println("I win.");
         } else {
            System.out.println("I lose.");
         }
      }
   }

/* Define an inner class to represent a move in the Nim game */

   private static class Move {
      int nTaken;
   }

/* Private constants */

   private static final int MAX_MOVE = 3;
   private static final int NO_GOOD_MOVE = -1;
   private static final int STARTING_COINS = 13;
   private static final Player STARTING_PLAYER = Player.HUMAN;

/* Private instance variables */

   private Console console;         /* Console for user interaction      */
   private int nCoins;              /* Number of coins left on the table */
   private Player currentPlayer;    /* Indicates whose turn it is        */

/* Main program */

   public static void main(String[] args) {
      new GeneralizedNim().run();
   }

}
