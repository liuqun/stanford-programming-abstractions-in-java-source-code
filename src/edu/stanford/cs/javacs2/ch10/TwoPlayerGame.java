/*
 * File: TwoPlayerGame.java
 * ------------------------
 * This class defines the abstract superclass for all two-player games.
 */

package edu.stanford.cs.javacs2.ch10;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import java.util.ArrayList;

public abstract class TwoPlayerGame {

/**
 * Creates a two-player game.
 */

   public TwoPlayerGame() {
      console = createConsole();
   }

/**
 * Creates the console for a two-player game.  Subclasses can override this
 * method to use a different console.
 *
 * @return A <code>Console</code> object
 */

   public Console createConsole() {
      return new SystemConsole();
   }

/* Common implementation of a run method that plays one game */

   public void run() {
      initGame();
      printInstructions();
      while (!gameIsOver()) {
         displayGame();
         if (getCurrentPlayer() == Player.HUMAN) {
            makeMove(getUserMove());
         } else {
            Move move = findBestMove(0);
            if (move == null) throw new RuntimeException("No legal moves");
            displayMove(move);
            makeMove(move);
         }
      }
      announceResult();
   }

/**
 * Finds and returns the best move for the current player.  The depth
 * parameter is used to limit the number of moves in the search.
 */

   public Move findBestMove(int depth) {
      ArrayList<Move> moveList = generateLegalMoves();
      Move bestMove = null;
      int minRating = WINNING_POSITION + 1;
      for (Move move : moveList) {
         makeMove(move);
         int moveRating = evaluatePosition(depth + 1);
         if (moveRating < minRating) {
            bestMove = move;
            minRating = moveRating;
         }
         retractMove(move);
      }
      if (bestMove != null) bestMove.setRating(-minRating);
      return bestMove;
   }

/**
 * Evaluates a position by returning the rating of the best move.
 */

   public int evaluatePosition(int depth) {
      if (gameIsOver() || depth >= MAX_DEPTH) {
         return evaluateStaticPosition();
      }
      return findBestMove(depth).getRating();
   }

   public void setCurrentPlayer(Player p) {
      currentPlayer = p;
   }

   public Player getCurrentPlayer() {
      return currentPlayer;
   }

   public void switchPlayer() {
      currentPlayer = (currentPlayer == Player.HUMAN) ? Player.COMPUTER
                                                      : Player.HUMAN;
   }

/**
 * Prints the argument value, allowing for the possibility of more output
 * on the same line.
 *
 * @param value The value to be displayed
 */

   public void print(Object value) {
      console.print(value);
   }

/**
 * Prints the end-of-line sequence to move to the next line.
 */

   public void println() {
      console.println();
   }

/**
 * Prints the value and then moves to the next line.
 *
 * @param value The value to be displayed
 */

   public void println(Object value) {
      console.println(value);
   }

/**
 * Formats and prints the argument values as specified by the
 * <code>format</code> string. The <code>printf</code> formats are
 * described in the <code>java.util.Formatter</code> class.
 *
 * @param format The format string
 * @param args The list of arguments to be formatted
 */

   public void printf(String format, Object... args) {
      console.printf(format, args);
   }

/**
 * Reads and returns a line of input, without including the end-of-line
 * characters that terminate the input.
 *
 * @return The next line of input as a <code>String</code>
 */

   public String nextLine() {
      return console.nextLine();
   }

/**
 * Prompts the user to enter a line of text, which is then returned
 * as the value of this method.
 *
 * @param prompt The prompt string to display to the user
 * @return The next line of input as a <code>String</code>
 */

   public String nextLine(String prompt) {
      return console.nextLine(prompt);
   }

/**
 * Reads and returns an integer value from the user.
 *
 * @return The value of the input interpreted as a decimal integer
 */

   public int nextInt() {
      return console.nextInt();
   }

/**
 * Prompts the user to enter an integer.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a decimal integer
 */

   public int nextInt(String prompt) {
      return console.nextInt(prompt);
   }

/**
 * Reads and returns a double-precision value from the user.
 *
 * @return The value of the input interpreted as a <code>double</code>
 */

   public double nextDouble() {
      return console.nextDouble();
   }

/**
 * Prompts the user to enter an double-precision number.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a <code>double</code>
 */

   public double nextDouble(String prompt) {
      return console.nextDouble();
   }

/* Abstract methods */

   public abstract void initGame();
   public abstract void printInstructions();
   public abstract void displayGame();
   public abstract void displayMove(Move move);
   public abstract Move getUserMove();
   public abstract ArrayList<Move> generateLegalMoves();
   public abstract void makeMove(Move move);
   public abstract void retractMove(Move move);
   public abstract boolean gameIsOver();
   public abstract void announceResult();
   public abstract int evaluateStaticPosition();

/* Constants */

   public static final int WINNING_POSITION = 1000;
   public static final int LOSING_POSITION = -1000;
   public static final int MAX_DEPTH = 100;

/* Private instance variables */

   private Console console;
   private Player currentPlayer;

}
