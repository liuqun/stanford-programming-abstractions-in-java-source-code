/*
 * File: Nim.java
 * --------------
 * This program simulates a simple variant of the game of Nim.  In this
 * version, the game starts with a pile of 13 coins on a table.  Players
 * then take turns removing 1, 2, or 3 coins from the pile.  The player
 * who takes the last coin loses.
 */

package edu.stanford.cs.javacs2.ch10;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class Nim {

   public void run() {
      console = new SystemConsole();
      printInstructions();
      nCoins = STARTING_COINS;
      currentPlayer = STARTING_PLAYER;
      while (nCoins > 1) {
         System.out.println("There are " + nCoins + " coins in the pile.");
         if (currentPlayer == Player.HUMAN) {
            nCoins -= getUserMove();
         } else {
            int nTaken = getComputerMove();
            System.out.println("I'll take " + nTaken + ".");
            nCoins -= nTaken;
         }
         switchPlayer();
      }
      announceResult();
   }

/**
 * Asks the user to enter a move and returns the number of coins taken.
 * If the move is not legal, the user is asked to reenter a valid move.
 */

   private int getUserMove() {
      int limit = (nCoins < MAX_MOVE) ? nCoins : MAX_MOVE;
      while (true) {
         int nTaken = console.nextInt("How many would you like? ");
         if (nTaken > 0 && nTaken <= limit) return nTaken;
         System.out.println("That's cheating!  Please choose " +
                            "between 1 and " + limit + ".");
         System.out.println("There are " + nCoins + " coins in the pile.");
      }
   }

/**
 * Figures out what move is best for the computer player and returns
 * the number of coins taken.  The method first calls findGoodMove
 * to see if a winning move exists.  If none does, the program takes
 * only one coin to give the human player more chances to make a mistake.
 */

   private int getComputerMove() {
      int nTaken = findGoodMove(nCoins);
      return (nTaken == NO_GOOD_MOVE) ? 1 : nTaken;
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
      new Nim().run();
   }

}
